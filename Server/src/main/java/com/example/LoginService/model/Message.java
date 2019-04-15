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
    String id;

    User fromUser;
    List<String> recipientsID;
    File image;


    public Message(){

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

    public void setId(String newId) { this.id = newId; }

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
