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

import com.example.natour21.Controller.authenticationController;
import com.example.natour21.R;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login);

        EditText email = findViewById(R.id.et_email);
        EditText password = findViewById(R.id.et_password);
        Button btnLoginwithFacebook = findViewById(R.id.btnLoginWithFacebook);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        CheckBox rememberMe = findViewById(R.id.rememberMe);


        SharedPreferences sharedPreferences = getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
        System.out.println("REMEMBER SHAREDPREFE: " + sharedPreferences.getString("remember",""));
        if(sharedPreferences.getString("remember","").equals("true")) {
            String accessToken = sharedPreferences.getString("accessToken", "");
            String refreshToken = sharedPreferences.getString("refreshToken", "");
            authenticationController.login(Login.this, accessToken, refreshToken);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("REMEMBER ME" + rememberMe.isChecked());
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



    private void LogWithFacebook() {

    }

}