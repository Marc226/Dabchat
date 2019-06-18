package com.example.main.interfaces;

import com.example.main.model.Message;
import com.example.main.model.User;

import java.util.List;

import androidx.lifecycle.MutableLiveData;


public interface IMessageRepository{
    void sendMessage (Message message);
    void receiveFriendsWithPendingMessages(String id, MutableLiveData<List<User>> data);
    List<User> getPendingFromUsers();
    void receiveMessagesByUserID(String id, MutableLiveData<List<Message>> data);
    void removeUserFromRecipients(User user, Message message, MutableLiveData<String> data);
    void clearPending();
    void removeUserFromRecipients(Message currentMessage);
}
