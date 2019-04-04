package com.example.demo.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class ImageModel {
    @Id
    private int imageId;

    private String fromUser;
    private List<String> toUsers;
    private String imageData;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public List<String> getToUsers() {
        return toUsers;
    }

    public void setToUsers(List<String> toUsers) {
        this.toUsers = toUsers;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
