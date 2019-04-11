package com.example.main.model;

import android.media.Image;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "image")
    private File image;


    @Ignore
    public Message (){
    }

    public Message(String id, File image) {
        this.id = id;
        this.image = image;
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


}
