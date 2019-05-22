package com.example.main.dao;

import android.content.Context;

import com.example.main.model.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class FriendDatabase extends RoomDatabase {

    private static FriendDatabase INSTANCE;

    public abstract FriendDao friendDao();

    public static FriendDatabase getMemoryDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FriendDatabase.class, "FriendList")
                    .build();
        }
        return INSTANCE;
    }
}
