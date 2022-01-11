package com.example.natour21.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btnRegister);

        EditText username = findViewById(R.id.et_username);
        EditText email = findViewById(R.id.et_email);
        EditText password = findViewById(R.id.et_password);
        EditText confirmPassword = findViewById(R.id.et_ConfirmPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticationController.registerNATOUR21(Register.this,username.getText().toString(),email.getText().toString(),password.getText().toString(),confirmPassword.getText().toString());
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
    }
}