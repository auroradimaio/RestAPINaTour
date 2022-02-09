package com.example.natour21.Item;

public class ReviewItem {
    private float Rating;
    private String Description;
    private String Username;

    public ReviewItem(float rating, String description, String username){
        Rating=rating;
        Description=description;
        Username = username;
    }

    public float getRating(){
        return Rating;
    }

    public String getDescription(){
        return Description;
    }

    public String getUsername(){return Username;}
}
