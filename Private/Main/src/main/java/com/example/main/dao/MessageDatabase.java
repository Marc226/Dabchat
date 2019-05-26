package com.example.main.dao;

import android.content.Context;
import com.example.main.model.ResponseMessage;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ResponseMessage.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {

    private static MessageDatabase INSTANCE;

    public abstract MessageDao messageDao();

    public static MessageDatabase getMemoryDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MessageDatabase.class, "MessageList")
                    .build();
        }
        return INSTANCE;
    }
}