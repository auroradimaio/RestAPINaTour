package com.example.natour21.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.natour21.Controller.authenticationController;
import com.example.natour21.Controller.chatController;
import com.example.natour21.Controller.reportController;
import com.example.natour21.Pusher.PusherManager;
import com.example.natour21.R;

public class HomeFragment  extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.show();

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView email = view.findViewById(R.id.txtEmail);
        TextView auth = view.findViewById(R.id.txtAuth);
        Button logout = view.findViewById(R.id.testLogout);


        chatController.onChatList = false;
        chatController.onSingleChat = false;
        reportController.onReportList = false;
        PusherManager.activity = getActivity();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticationController.logout(getActivity(), false);
            }
        });

        email.setText(authenticationController.user_username);
        auth.setText(authenticationController.auth);

        return view;
    }

}
