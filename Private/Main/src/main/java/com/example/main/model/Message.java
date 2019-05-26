package com.example.main.model;

import android.media.Image;
import android.util.Base64;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Message {


    private String id;
    private User fromUser;
    private List<String> recipientsID;
    private String image;


    public Message (){
    }

    public Message(String id, byte[] image) {
        this.id = id;
        this.image = Base64.encodeToString(image, Base64.DEFAULT);
        this.recipientsID = new ArrayList<>();
    }

    public void addRecipients(String... reps) {
        for(String str : reps) {
            recipientsID.add(str);
        }
    }

    public byte[] getImage() {
        return Base64.decode(image, Base64.DEFAULT);
    }

    public void setImage(byte[] image) {
        this.image = Base64.encodeToString(image, Base64.DEFAULT);

    }

    public String getId() {
        return id;
    }

    public void addRecipient(String id){
        this.recipientsID.add(id);
    }


    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public List<String> getRecipientsID() {return recipientsID;}
}
