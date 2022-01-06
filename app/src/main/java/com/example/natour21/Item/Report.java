package com.example.natour21.Item;

public class Report {

    private Long id;
    private String sender;
    private String title;
    private String description;
    private String postOwner;
    private String postTitle;
    private String response;

    public Report(Long id,String sender, String title, String description, String postOwner, String postTitle, String response) {
        this.id=id;
        this.sender = sender;
        this.title = title;
        this.description = description;
        this.postOwner = postOwner;
        this.postTitle = postTitle;
        this.response = response;
    }

    public Long getId(){
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getResponse() {
        return response;
    }

    public String getPostOwner() {
        return postOwner;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
