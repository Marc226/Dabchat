package com.example.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
@Keep
public class ResponseMessage implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "from_mail")
    private String fromUserMail;
    @ColumnInfo(name = "image")
    private String image;

    public ResponseMessage() {
    }

    protected ResponseMessage(Parcel in) {
        id = in.readString();
        fromUserMail = in.readString();
        image = in.readString();
    }

    public static final Creator<ResponseMessage> CREATOR = new Creator<ResponseMessage>() {
        @Override
        public ResponseMessage createFromParcel(Parcel in) {
            return new ResponseMessage(in);
        }

        @Override
        public ResponseMessage[] newArray(int size) {
            return new ResponseMessage[size];
        }
    };

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getFromUserMail() {
        return fromUserMail;
    }

    public void setFromUserMail(String fromUserMail) {
        this.fromUserMail = fromUserMail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(fromUserMail);
        dest.writeString(image);
    }
}
