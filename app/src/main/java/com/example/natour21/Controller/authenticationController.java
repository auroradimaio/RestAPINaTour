package com.example.natour21.Controller;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.login.LoginManager;
import com.example.natour21.API.User.UserAPI;
import com.example.natour21.Activity.Login;
import com.example.natour21.Activity.homePage;
import com.example.natour21.Enumeration.Auth;
import com.example.natour21.R;
import com.example.natour21.Volley.VolleyCallback;
import org.json.JSONException;
import org.json.JSONObject;

public class authenticationController {

    public static String accessToken;
    public static String refreshToken;
    public static String userEmail;
    public static String auth;

    public static void login(AppCompatActivity activity, String email, String password, boolean rememberMe)
    {
        if (isNetworkConnected(activity.getApplication().getApplicationContext())) {
            if (email.length() > 0 && password.length() > 0) {
                if(isEmailValid(email)) {
                    UserAPI.checkAuth(activity,email, new VolleyCallback() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.getString("status").equals("OK"))
                                {
                                    if(jsonObject.getString("result").equals(Auth.NATOUR21.toString())) {
                                        ProgressDialog progressDialog = new ProgressDialog(activity);
                                        progressDialog.setMessage("Accesso in corso...");
                                        progressDialog.show();
                                        progressDialog.setCancelable(false);
                                        auth = jsonObject.getString("result");
                                        UserAPI.login(activity, email, password, new VolleyCallback() {
                                            @Override
                                            public void onSuccess(String response) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    if (jsonObject.getString("status").equals("OK")) {
                                                        accessToken = jsonObject.getJSONObject("result").getString("accessToken");
                                                        refreshToken = jsonObject.getJSONObject("result").getString("refreshToken");
                                                        userEmail = jsonObject.getJSONObject("result").getString("email");
                                                        if (rememberMe) {
                                                            SharedPreferences sharedPreferences = activity.getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                                            editor.putString("remember", "true");
                                                            editor.putString("email", userEmail);
                                                            editor.putString("accessToken", accessToken);
                                                            editor.putString("refreshToken", refreshToken);
                                                            editor.putString("auth", auth);
                                                            editor.apply();
                                                        }
                                                        progressDialog.dismiss();
                                                        activity.startActivity(new Intent(activity, homePage.class));
                                                    } else if (jsonObject.getString("status").equals("FAILED")) {
                                                        progressDialog.dismiss();
                                                        showMessageDialog(activity, jsonObject.getString("result"), null);
                                                    }
                                                } catch (JSONException jsonException) {
                                                    progressDialog.dismiss();
                                                    showMessageDialog(activity, "Errore nel recupero delle credenziali", null);
                                                }
                                            }

                                            @Override
                                            public void onError(String response) {
                                                progressDialog.dismiss();
                                                showMessageDialog(activity, "Errore nel recupero delle credenziali, riprovare più tardi", null);
                                            }
                                        });
                                    }else
                                    {
                                        showMessageDialog(activity, "Email non registrata tramite autenticazione NaTour21", null);
                                    }
                                }
                                else if(jsonObject.getString("status").equals("FAILED"))
                                {
                                    showMessageDialog(activity, jsonObject.getString("result"), null);
                                }
                            } catch (JSONException jsonException) {
                                showMessageDialog(activity, "Errore nel recupero delle credenziali", null);
                            }
                        }

                        @Override
                        public void onError(String response) {
                            showMessageDialog(activity, "Errore nel recupero delle credenziali, riprovare più tardi", null);
                        }
                    });
                }else
                {
                    showMessageDialog(activity, "Email invalida", null);
                }
            } else {
                showMessageDialog(activity, "Inserire le credenziali di accesso", null);
            }
        }else
        {
            showMessageDialog(activity, "Per favore controlla la tua connessione di rete", null);
        }
    }

    public static void loginWithFacebook(AppCompatActivity activity, String email, String firstName, String lastName)
    {
        UserAPI.loginFacebook(activity,email,firstName,lastName, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK"))
                    {
                        ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Accesso in corso...");
                        progressDialog.show();
                        progressDialog.setCancelable(false);
                        UserAPI.login(activity, email, email + firstName + lastName + "FACEBOOK_AUTH", new VolleyCallback() {
                            @Override
                            public void onSuccess(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getString("status").equals("OK")) {
                                        accessToken = jsonObject.getJSONObject("result").getString("accessToken");
                                        refreshToken = jsonObject.getJSONObject("result").getString("refreshToken");
                                        userEmail = jsonObject.getJSONObject("result").getString("email");
                                        auth = Auth.FACEBOOK.toString();
                                        SharedPreferences sharedPreferences = activity.getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("remember", "true");
                                        editor.putString("email", userEmail);
                                        editor.putString("accessToken", accessToken);
                                        editor.putString("refreshToken", refreshToken);
                                        editor.putString("auth", auth);
                                        editor.apply();
                                        progressDialog.dismiss();
                                        activity.startActivity(new Intent(activity, homePage.class));
                                    } else if (jsonObject.getString("status").equals("FAILED")) {
                                        progressDialog.dismiss();
                                        showMessageDialog(activity, jsonObject.getString("result"), null);
                                    }
                                } catch (JSONException jsonException) {
                                    progressDialog.dismiss();
                                    showMessageDialog(activity, "Errore nel recupero delle informazioni", null);
                                }
                            }

                            @Override
                            public void onError(String response) {
                                showMessageDialog(activity, "Errore nel recupero delle informazioni", null);
                            }
                        });
                    }
                    else if(jsonObject.getString("status").equals("FAILED"))
                    {
                        showMessageDialog(activity, jsonObject.getString("result"), null);
                    }
                } catch (JSONException jsonException) {
                    showMessageDialog(activity, "Errore nel recupero delle informazioni", null);
                }
            }

            @Override
            public void onError(String response) {
                showMessageDialog(activity, "Errore nel recupero delle informazioni, riprovare più tardi", null);
            }
        });
    }

    public static void loginWithGoogle(AppCompatActivity activity, String email, String firstName, String lastName)
    {
        UserAPI.loginGoogle(activity,email,firstName,lastName, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("OK"))
                    {
                        ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Accesso in corso...");
                        progressDialog.show();
                        progressDialog.setCancelable(false);
                        UserAPI.login(activity, email, email + firstName + lastName + "GOOGLE_AUTH", new VolleyCallback() {
                            @Override
                            public void onSuccess(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getString("status").equals("OK")) {
                                        accessToken = jsonObject.getJSONObject("result").getString("accessToken");
                                        refreshToken = jsonObject.getJSONObject("result").getString("refreshToken");
                                        userEmail = jsonObject.getJSONObject("result").getString("email");
                                        auth = Auth.GOOGLE.toString();
                                        SharedPreferences sharedPreferences = activity.getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("remember", "true");
                                        editor.putString("email", userEmail);
                                        editor.putString("accessToken", accessToken);
                                        editor.putString("refreshToken", refreshToken);
                                        editor.putString("auth", auth);
                                        editor.apply();
                                        progressDialog.dismiss();
                                        activity.startActivity(new Intent(activity, homePage.class));
                                    } else if (jsonObject.getString("status").equals("FAILED")) {
                                        progressDialog.dismiss();
                                        showMessageDialog(activity, jsonObject.getString("result"), null);
                                    }
                                } catch (JSONException jsonException) {
                                    progressDialog.dismiss();
                                    showMessageDialog(activity, "Errore nel recupero delle informazioni", null);
                                }
                            }

                            @Override
                            public void onError(String response) {
                                showMessageDialog(activity, "Errore nel recupero delle informazioni", null);
                            }
                        });
                    }
                    else if(jsonObject.getString("status").equals("FAILED"))
                    {
                        showMessageDialog(activity, jsonObject.getString("result"), null);
                    }
                } catch (JSONException jsonException) {
                    showMessageDialog(activity, "Errore nel recupero delle informazioni", null);
                }
            }

            @Override
            public void onError(String response) {
                showMessageDialog(activity, "Errore nel recupero delle informazioni, riprovare più tardi", null);
            }
        });
    }


    public static void register(AppCompatActivity activity, String firstName, String lastName, String email, String password, String confirmPassword, String auth) {
        if(isNetworkConnected(activity.getApplication().getApplicationContext())) {
            if(firstName.length() > 0 && lastName.length() > 0 && email.length() > 0 && password.length() > 0 && confirmPassword.length() > 0) {
                if(isEmailValid(email)) {
                    if(password.length() >= 6) {
                        if(password.equals(confirmPassword)) {

                            ProgressDialog progressDialog = new ProgressDialog(activity);
                            progressDialog.setMessage("Registrazione in corso...");
                            progressDialog.show();
                            progressDialog.setCancelable(false);


                            UserAPI.register(activity, firstName, lastName, email, password, auth, new VolleyCallback() {
                                @Override
                                public void onSuccess(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if(jsonObject.getString("status").equals("OK")) {
                                            showMessageDialog(activity, "Registrazione effettuata con successo.", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    activity.startActivity(new Intent(activity, Login.class));
                                                }
                                            });
                                        }else if(jsonObject.getString("status").equals("FAILED"))
                                        {
                                            showMessageDialog(activity, "Email già registrata.", null);
                                        }
                                        progressDialog.dismiss();
                                    } catch (JSONException jsonException) {
                                        showMessageDialog(activity, "Errore nel recupero delle credenziali.", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                activity.startActivity(new Intent(activity, Login.class));
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onError(String response) {
                                    showMessageDialog(activity, "Errore durante la registrazione, riprovare più tardi.", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            activity.startActivity(new Intent(activity, Login.class));
                                        }
                                    });
                                }
                            });
                        }else
                        {
                            showMessageDialog(activity, "Le password non corrispondono.", null);
                        }
                    }else{
                        showMessageDialog(activity, "La password deve essere composta da almeno 6 caratteri", null);
                    }
                }else
                {
                    showMessageDialog(activity, "Inserire un'email valida.", null);
                }
            }
            else
            {
                showMessageDialog(activity, "Per poter procedere con la registrazione è necessario fornire le informazioni richieste", null);
            }
        }
        else
        {
            showMessageDialog(activity, "Per favore controlla la tua connessione di rete.", null);
        }
    }

    public static void logout(AppCompatActivity activity, String auth_)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        activity.startActivity(new Intent(activity, Login.class));

        if(auth_.equals(Auth.FACEBOOK.toString()))
        {
            LoginManager.getInstance().logOut();
        }
    }

    private static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void showMessageDialog(AppCompatActivity activity, String message, View.OnClickListener clickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customdialog, viewGroup, false);

        TextView messageTV = dialogView.findViewById(R.id.messagetxt);
        messageTV.setText(message);

        builder.setView(dialogView);

        AlertDialog alertDialog = builder.create();

        Button okButton = dialogView.findViewById(R.id.okButton);
        if(clickListener == null)
        {
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }else {
            okButton.setOnClickListener(clickListener);
        }
        alertDialog.show();
    }

    private static boolean isNetworkConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void login(AppCompatActivity activity, String accessToken_, String refreshToken_, String userEmail_, String auth_) {
        accessToken = accessToken_;
        refreshToken = refreshToken_;
        userEmail = userEmail_;
        auth = auth_;
        activity.startActivity(new Intent(activity, homePage.class));
    }
}
