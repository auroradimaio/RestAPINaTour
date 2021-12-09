package com.example.natour21.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.natour21.Controller.authenticationController;
import com.example.natour21.R;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticationController.logout(homePage.this, authenticationController.auth);
            }
        });

        TextView accesstoken = findViewById(R.id.accessToken);
        TextView refreshtoken = findViewById(R.id.refreshToken);
        TextView email = findViewById(R.id.email);
        TextView auth = findViewById(R.id.auth);

        accesstoken.setText("AccessToken: " + authenticationController.accessToken);
        refreshtoken.setText("RefreshToken: " + authenticationController.refreshToken);
        email.setText("Email: " + authenticationController.userEmail);
        auth.setText("Auth: " + authenticationController.auth);

    }
}