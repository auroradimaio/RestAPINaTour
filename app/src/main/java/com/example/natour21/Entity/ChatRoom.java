package com.example.natour21.Entity;

public class ChatRoom {

    private String username;
    private String lastMessage;
    private Long timeLastMessage;

    public ChatRoom(String username, String lastMessage, Long timeLastMessage) {
        this.username = username;
        this.lastMessage = lastMessage;
        this.timeLastMessage = timeLastMessage;
    }

    public String getUsername() {
        return username;
    }


    public String getLastMessage() {
        return lastMessage;
    }

    public Long getTimeLastMessage() {
        return timeLastMessage;
    }

}
