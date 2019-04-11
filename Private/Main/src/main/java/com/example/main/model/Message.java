package com.example.main.model;

import android.media.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Message {


    private String id;
    private User fromUser;
    private List<String> recipientsID;
    private File image;


    public Message (){
    }

    public Message(String id, File image) {
        this.id = id;
        this.image = image;
        this.recipientsID = new ArrayList<>();
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
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
}
