package com.example.main.dao;

import com.example.main.model.Message;
import com.example.main.model.ResponseMessage;
import com.example.main.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMessage(ResponseMessage message);

    @Delete
    void removeMessage(ResponseMessage message);

    @Query("SELECT * FROM responsemessage")
    LiveData<List<ResponseMessage>> getAllMessages();

    @Query("DELETE FROM responsemessage")
    void dropTable();
}
