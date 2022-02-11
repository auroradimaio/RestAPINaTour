package com.example.natour21.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.natour21.Adapter.ChatListAdapter;
import com.example.natour21.Controller.ChatController;
import com.example.natour21.Controller.ReportController;
import com.example.natour21.Item.ChatRoom;
import com.example.natour21.R;

import java.util.List;

public class ChatListFragment extends Fragment  {

    private static RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        recyclerView = view.findViewById(R.id.chatListRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ChatListAdapter());

        ImageButton newMessage = view.findViewById(R.id.btnNewChat);

        newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatController.openNewMessage(getActivity());
            }
        });


        return view;
    }

    public static void updateUI(List<ChatRoom> chatRoomList) {
        ((ChatListAdapter) recyclerView.getAdapter()).update(chatRoomList);
    }

    @Override
    public void onResume() {
        super.onResume();

        ChatController.chattingWith = "";
        ChatController.onChatList = true;
        ChatController.onSingleChat = false;
        ReportController.onReportList = false;
        ChatController.chatListActivity = getActivity();
        ChatController.getChatList();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}