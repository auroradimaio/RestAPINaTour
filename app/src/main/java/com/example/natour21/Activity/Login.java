package com.example.natour21.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class Login extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        callbackManager = CallbackManager.Factory.create();


        EditText email = findViewById(R.id.et_email);
        EditText password = findViewById(R.id.et_password);
        Button btnLoginwithFacebook = findViewById(R.id.btnLoginWithFacebook);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        CheckBox rememberMe = findViewById(R.id.rememberMe);

        SharedPreferences sharedPreferences = getSharedPreferences("rememberMe", Context.MODE_PRIVATE);

        if(sharedPreferences.getString("remember","").equals("true")) {
            String accessToken = sharedPreferences.getString("accessToken", "");
            String refreshToken = sharedPreferences.getString("refreshToken", "");
            String userEmail = sharedPreferences.getString("email", "");
            String auth = sharedPreferences.getString("auth", "");
            authenticationController.login(Login.this, accessToken, refreshToken, userEmail, auth);
        }

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
        btnLoginwithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogWithFacebook();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void LogWithFacebook() {

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
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
                    }
                });

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }

}