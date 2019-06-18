package com.example.main.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.main.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase INSTANCE;

    public abstract UserDao userDao();

    public static UserDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "CurrentUserDB")
                    .build();
        }
        return INSTANCE;
    }
}
