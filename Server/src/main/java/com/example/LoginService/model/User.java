package com.example.LoginService.model;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class User {

    @Id
    private String id;

    private String firstName = "";
    private String lastName = "";
    private String passWord;
    private String mail;
    private int phoneNumber;
    private List<String> frientList;


    public User(){
    }

    public User(String id, String passWord, String mail) {
        this.id = id;
        this.passWord = passWord;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String eMail) {
        this.mail = eMail;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getFrientList() {
        return frientList;
    }

    public void createFriendList() {
        frientList = new ArrayList();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        return id.equals(((User) obj).id);
    }

    public void setFrientList(List<String> frientList) {
        this.frientList = frientList;
    }
}
