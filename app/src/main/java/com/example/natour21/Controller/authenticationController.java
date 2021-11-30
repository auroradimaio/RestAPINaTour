package com.example.natour21.Controller;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.example.natour21.API.User.UserAPI;
import com.example.natour21.Activity.Login;
import com.example.natour21.Activity.homePage;
import com.example.natour21.R;
import com.example.natour21.Volley.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class authenticationController {

    public static String accessToken;
    public static String refreshToken;

    public static void login(Activity activity,String email, String password, boolean rememberMe)
    {

        if (isNetworkConnected(activity.getApplication().getApplicationContext())) {
            if (!email.equals("") && !password.equals("")) {

                ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Accesso in corso...");
                progressDialog.show();
                progressDialog.setCancelable(false);

                UserAPI.login(activity, email, password, new VolleyCallback() {

                    @Override
                    public void onSuccess(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getString("status").equals("OK")) {
                                accessToken = jsonObject.getJSONObject("result").getString("accessToken");
                                refreshToken = jsonObject.getJSONObject("result").getString("refreshToken");
                                if (rememberMe) {
                                    SharedPreferences sharedPreferences = activity.getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("remember", "true");
                                    editor.putString("accessToken", accessToken);
                                    editor.putString("refreshToken", refreshToken);
                                    editor.apply();
                                }
                                progressDialog.dismiss();
                                activity.startActivity(new Intent(activity, homePage.class));
                            }else if(jsonObject.getString("status").equals("FAILED"))
                            {
                                progressDialog.dismiss();
                                showMessageDialog(activity, "Email o password errata.", null);
                            }
                        } catch (JSONException jsonException) {
                            showMessageDialog(activity, "Errore nel recupero delle credenziali.", null);
                        }
                    }

                    @Override
                    public void onError(String response) {
                        showMessageDialog(activity, "Errore nel recupero delle credenziali, riprovare più tardi.", null);
                    }
                });
            } else {
                showMessageDialog(activity, "Inserire le credenziali di accesso", null);
            }
        }else
        {
            showMessageDialog(activity, "Per favore controlla la tua connessione di rete.", null);
        }
    }

    public static void register(Activity activity, String username, String email, String password, String confirmPassword, String auth) {
        if(isNetworkConnected(activity.getApplication().getApplicationContext())) {
            if(!username.equals("") && !email.equals("") && !password.equals("") && !confirmPassword.equals("")) {
                if(isEmailValid(email)) {
                    if(password.length() >= 6) {
                        if(password.equals(confirmPassword)) {

                            ProgressDialog progressDialog = new ProgressDialog(activity);
                            progressDialog.setMessage("Registrazione in corso...");
                            progressDialog.show();
                            progressDialog.setCancelable(false);


                            UserAPI.register(activity, username, email, password, auth, new VolleyCallback() {
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

    public static void logout(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        activity.startActivity(new Intent(activity, Login.class));
    }

    private static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    protected static void showMessageDialog(Activity activity, String message, View.OnClickListener clickListener) {

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

    public static void login(Activity activity, String accessToken_, String refreshToken_) {
        accessToken = accessToken_;
        refreshToken = refreshToken_;
        activity.startActivity(new Intent(activity, homePage.class));
    }
}
