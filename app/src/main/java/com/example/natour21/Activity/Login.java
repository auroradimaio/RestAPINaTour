package com.example.natour21.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.natour21.Controller.authenticationController;
import com.example.natour21.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class Login extends AppCompatActivity {

    //Facebook
    CallbackManager callbackFacebook;
    //Google
    GoogleSignInClient googleSignInClient;

    ActivityResultLauncher<Intent> mLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        SharedPreferences sharedPreferences = getSharedPreferences("rememberMe", Context.MODE_PRIVATE);

        if(sharedPreferences.getString("remember","").equals("true")) {
            String accessToken = sharedPreferences.getString("accessToken", "");
            String refreshToken = sharedPreferences.getString("refreshToken", "");
            String userEmail = sharedPreferences.getString("email", "");
            String auth = sharedPreferences.getString("auth", "");
            authenticationController.login(Login.this, accessToken, refreshToken, userEmail, auth);
        }


        //Facebook init
        callbackFacebook = CallbackManager.Factory.create();

        //Google init
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("284354529342-6j420h8tdg4m860gq6a6es4iactng2j7.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        mLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        googleSignInClient.signOut();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                        LoginWithGoogle(task);
                    }
                });


        EditText email = findViewById(R.id.et_email);
        EditText password = findViewById(R.id.et_password);
        Button btnLoginWithFacebook = findViewById(R.id.btnLoginWithFacebook);
        Button btnLoginWithGoogle = findViewById(R.id.btnLoginWithGoogle);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        CheckBox rememberMe = findViewById(R.id.rememberMe);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticationController.login(Login.this, email.getText().toString(), password.getText().toString(), rememberMe.isChecked());
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        // Callback registration
        btnLoginWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginWithFacebook();
            }
        });

        btnLoginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                mLauncher.launch(signInIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackFacebook.onActivityResult(requestCode, resultCode, data);
    }

    private void LoginWithFacebook() {

        callbackFacebook = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackFacebook,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        try {
                                            String email = response.getJSONObject().getString("email");
                                            String firstName = response.getJSONObject().getString("first_name");
                                            String lastName = response.getJSONObject().getString("last_name");

                                            authenticationController.loginWithFacebook(Login.this, email, firstName, lastName);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,email,first_name,last_name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        authenticationController.showMessageDialog(Login.this, "Errore durante l'autenticazione", null);
                    }
                });

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }

    private void LoginWithGoogle(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            authenticationController.loginWithGoogle(Login.this,account.getEmail(),account.getGivenName(),account.getFamilyName());
        } catch (ApiException e) {

        }
    }

}