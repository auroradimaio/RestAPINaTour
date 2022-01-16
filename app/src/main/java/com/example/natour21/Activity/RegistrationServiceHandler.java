package com.example.natour21.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.R;

public class RegistrationServiceHandler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_handler);

        String email = getIntent().getExtras().getString("email");
        String id = getIntent().getExtras().getString("id");
        boolean isFacebook = getIntent().getExtras().getBoolean("isFacebook");


        EditText username = findViewById(R.id.et_username_registration);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFacebook)
                {
                    AuthenticationController.registerFACEBOOK(RegistrationServiceHandler.this,id, email, username.getText().toString());
                }
                else
                {
                    AuthenticationController.registerGOOGLE(RegistrationServiceHandler.this, email, username.getText().toString());
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationServiceHandler.this, Login.class));
            }
        });

    }
}