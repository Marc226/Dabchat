package com.example.main.model;


import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "first_name")
    private String firstName = "";
    @ColumnInfo(name = "last_name")
    private String lastName = "";
    @ColumnInfo(name = "pw")
    private String passWord;
    @ColumnInfo(name = "e_mail")
    private String mail;
    @ColumnInfo(name = "phone_number")
    private int phoneNumber;

    @Ignore
    public User(){
    }

    public User(String passWord, String mail, int phoneNumber) {
        this.passWord = passWord;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof User)) return false;
        User other = (User) obj;

        return this.getMail().equals(((User) obj).getMail());
    }
}

