package com.example.main.dao;

import com.example.main.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFriend(User user);

    @Delete
    void removeFriend(User user);

    @Query("SELECT * FROM user")
    List<User> getAllFriends();

    @Query("SELECT * FROM user WHERE id LIKE :id")
    User getFriend(String id);

    @Query("DELETE FROM user")
    public void dropTable();
}
