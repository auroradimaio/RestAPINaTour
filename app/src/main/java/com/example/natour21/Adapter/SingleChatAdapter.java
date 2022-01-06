package com.example.natour21.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.natour21.Controller.AuthenticationController;
import com.example.natour21.Item.Message;
import com.example.natour21.R;

import java.util.List;


public class SingleChatAdapter extends RecyclerView.Adapter<SingleChatAdapter.MessageViewHolder> {

    private List<Message> chatMessages;

    public SingleChatAdapter(List<Message> chatMessages){
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sender_message, parent, false);
            MessageViewHolder messageViewHolder = new MessageViewHolder(view);
            return messageViewHolder;
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiver_message, parent, false);
            MessageViewHolder messageViewHolder = new MessageViewHolder(view);
            return messageViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        Message message = chatMessages.get(position);

        holder.message.setText(message.getMessage());
    }

    @Override
    public int getItemViewType(int position) {
        Message message = chatMessages.get(position);
        if(message.getEmail().equals(AuthenticationController.user_username))
        {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView message;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_content);
        }
    }
}
