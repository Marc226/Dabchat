package com.example.main.interfaces;

import com.example.main.model.Message;
import com.example.main.model.User;

import java.util.List;

import androidx.lifecycle.MutableLiveData;


public interface IMessageRepository{
    public void sendMessage (Message message, MutableLiveData<String> data);
    public void receiveMessagesByUserID(String id, MutableLiveData<String> data);
    public void receiveFriendsWithPendingMessages(String id, MutableLiveData<List<User>> data);
    public List<User> getPendingFromUsers();
}
