package com.example.natour21.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.natour21.Adapter.SingleChatAdapter;
import com.example.natour21.Controller.ChatController;
import com.example.natour21.Controller.ReportController;
import com.example.natour21.Item.Message;
import com.example.natour21.R;

import java.util.List;

public class SingleChat extends AppCompatActivity {

    private TextView userFullName;
    private EditText messageContent;
    private static RecyclerView recyclerView;
    private ImageView sendMessageButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        getSupportActionBar().hide();

        ChatController.onChatList = false;
        ChatController.onSingleChat = true;
        ReportController.onReportList = false;
        ChatController.chattingWith = getIntent().getExtras().getString("username");
        ChatController.singleChatActivity = this;
        recyclerView = findViewById(R.id.list_message_chat);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        backButton = findViewById(R.id.backButtonSingleChat);
        messageContent = findViewById(R.id.sendMessageEditText);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageContent.getText().length() > 0) {
                    ChatController.sendMessage(messageContent.getText().toString());
                    messageContent.setText("");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);

        userFullName = findViewById(R.id.userFullName);

        userFullName.setText(getIntent().getExtras().getString("username"));

        ChatController.getSingleChat(getIntent().getExtras().getString("username"));
    }


    public static void updateUI(List<Message> singleChat) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatController.singleChatActivity, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new SingleChatAdapter(singleChat));
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void finish() {
        super.finish();
    }
}