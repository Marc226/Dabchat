package com.example.main.interfaces;

import com.example.main.model.Message;
import com.example.main.model.ResponseMessage;
import com.example.main.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public interface IMessageRepository{
    public void sendMessage (Message message, MutableLiveData<String> data);
    public void receiveFriendsWithPendingMessages(String id, MutableLiveData<List<User>> data);
    public List<User> getPendingFromUsers();
    LiveData<List<ResponseMessage>> receiveMessagesByUserID(String id);
    public void clearPending();

    public void removeUserFromRecipients(ResponseMessage currentMessage);
}
