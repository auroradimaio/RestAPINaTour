package com.example.natour21.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.R;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button btnChange = findViewById(R.id.btnUpdate);
        EditText password = findViewById(R.id.passwordForget);
        EditText confirmPassword = findViewById(R.id.confirmPasswordForget);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticationController.changePassword(ChangePassword.this, getIntent().getExtras().getString("username"),
                        password.getText().toString(),
                        confirmPassword.getText().toString());
            }
        });
    }
}
