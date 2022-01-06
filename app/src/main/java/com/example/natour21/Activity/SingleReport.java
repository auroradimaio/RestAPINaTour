package com.example.natour21.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.Controller.ReportController;
import com.example.natour21.R;

public class SingleReport extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private static TextView sender;
    private static TextView response;
    private static TextView responseUsername;
    private static EditText messageResponse;
    private static Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_report);

        getSupportActionBar().setTitle(getIntent().getExtras().getString("post_title"));
        Long reportId = getIntent().getExtras().getLong("report_id");


        title = findViewById(R.id.titleReport);
        title.setText(getIntent().getExtras().getString("report_title"));

        description = findViewById(R.id.descriptionReport);
        sender = findViewById(R.id.senderReport);

        description.setText(getIntent().getExtras().getString("report_description"));

        response = findViewById(R.id.responseReport);
        responseUsername = findViewById(R.id.responseUsername);

        messageResponse = findViewById(R.id.message_report_response);

        btnSend = findViewById(R.id.btnSendResponse);

        if(getIntent().getExtras().getString("report_sender").equals(AuthenticationController.user_username))
        {
            sender.setText("Tu");
            messageResponse.setVisibility(View.GONE);
            btnSend.setVisibility(View.GONE);
            if(!getIntent().getExtras().getString("response").equals("")) {
                responseUsername.setText(getIntent().getExtras().getString("post_owner"));
                response.setText(getIntent().getExtras().getString("response"));
            }else
            {
                responseUsername.setText("");
                response.setText("");
            }

        }else if(getIntent().getExtras().getString("post_owner").equals(AuthenticationController.user_username))
        {
            sender.setText(getIntent().getExtras().getString("report_sender"));

            if(!getIntent().getExtras().getString("response").equals(""))
            {
                messageResponse.setVisibility(View.GONE);
                btnSend.setVisibility(View.GONE);
                responseUsername.setText("Tu");
                response.setText(getIntent().getExtras().getString("response"));
            }
            else
            {
                responseUsername.setVisibility(View.GONE);
                response.setVisibility(View.GONE);
                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReportController.sendResponse(SingleReport.this, reportId, messageResponse.getText().toString());
                        messageResponse.setText("");
                    }
                });
            }
        }
    }


    public static void updateUI(String responseMessage) {
        messageResponse.setVisibility(View.GONE);
        btnSend.setVisibility(View.GONE);
        responseUsername.setVisibility(View.VISIBLE);
        response.setVisibility(View.VISIBLE);
        responseUsername.setText("Tu");
        response.setText(responseMessage);
    }
}