package com.example.natour21.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.R;

public class VerifyOTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        String code = getIntent().getExtras().getString("code");
        String username = getIntent().getExtras().getString("username");

        Button btnVerify = findViewById(R.id.btnVerify);
        EditText etCode = findViewById(R.id.codeForgetPassword);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticationController.openChangePassword(VerifyOTP.this,username, code, etCode.getText().toString());
            }
        });
    }
}