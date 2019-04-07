package com.example.MessageService.model;

import com.example.LoginService.model.User;
import org.springframework.data.annotation.Id;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Message {

    @Id
    String id;
    List<User> recipients;
    File image;

    public Message(String id, File image) {
        this.id = id;
        this.image = image;
        this.recipients = new ArrayList<>();
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

    public void addRecipient(User user){
        this.recipients.add(user);
    }

    public User getUser(String id){
        for(User user: recipients){
            if(user.getId().contentEquals(id)){
                return user;
            }
        }
        return null;
    }
}
