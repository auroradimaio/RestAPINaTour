package com.example.natour21.Item;

public class ChatRoom {

    private String username;
    private String lastMessage;

    public ChatRoom(String username, String lastMessage) {
        this.username = username;
        this.lastMessage = lastMessage;
    }

    public String getUsername() {
        return username;
    }


    public String getLastMessage() {
        return lastMessage;
    }

}
