package com.example.main.model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.File;
import java.io.Serializable;
import android.util.Base64;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Keep;


@Keep public class Message implements Parcelable {


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

    public Message(Parcel parcel){
        this.id = parcel.readString();
        this.fromUser = (User) parcel.readSerializable();
        this.recipientsID = parcel.readArrayList(null);
        this.image = parcel.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeSerializable(fromUser);
        dest.writeList(recipientsID);
        dest.writeString(image);

    }
}
