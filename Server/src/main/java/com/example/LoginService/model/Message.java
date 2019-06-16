package com.example.LoginService.model;

import com.example.LoginService.model.User;
import com.example.LoginService.service.RegisterService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Message {

    @Id
    private String id;

    private User fromUser;
    private List<String> recipientsID;
    private String image;


    public Message(){

    }

    public Message(String id, String image) {
        this.id = id;
        this.image = image;
        this.recipientsID = new ArrayList<>();
    }

    public List<String> getRecipientsID() {
        return recipientsID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String newId) { this.id = newId; }

    public void addRecipient(String id){
        if(this.recipientsID==null)
            this.recipientsID = new ArrayList<>();

        this.recipientsID.add(id);
    }

    public void removeRecipient(String id) {
        recipientsID.remove(id);
    }

    public int recipientCount() {
        return this.recipientsID.size();
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

}
