package com.example.main.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.main.model.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrentUser(User user);

    @Delete
    void removeCurrentUser(User user);

    @Query("SELECT * FROM user")
    User getCurrentUser();

    @Query("SELECT * FROM user")
    LiveData<User> autoLogin();

    @Query("DELETE FROM user")
    void dropTable();
}
