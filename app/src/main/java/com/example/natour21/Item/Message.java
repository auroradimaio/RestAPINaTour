package com.example.natour21.Item;

public class Message {

    private String email;
    private String message;
    private Long timeMessage;

    public Message(String email, String message, Long timeMessage) {
        this.email = email;
        this.message = message;
        this.timeMessage = timeMessage;
    }

    public String getEmail() {
        return email;
    }


    public String getMessage() {
        return message;
    }

    public Long getTimeMessage() {
        return timeMessage;
    }

}
