package com.example.natour21.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.R;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Button btnSend = findViewById(R.id.btnGoToCodeVerify);
        EditText etEmail = findViewById(R.id.emailForgetPassword);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticationController.openVerifyOTP(ForgetPassword.this, etEmail.getText().toString());
            }
        });
    }
}
