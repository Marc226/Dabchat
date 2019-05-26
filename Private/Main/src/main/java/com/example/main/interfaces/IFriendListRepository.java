package com.example.main.interfaces;

import com.example.main.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface IFriendListRepository {
    void addFriend(User user, String currentUserId, MutableLiveData<String> data);
    LiveData<List<User>> getAllFriends(User currentUser);
    LiveData<User> getSpecificFriend(String id);
    void closeDB();
}
