package com.example.main.interfaces;

import com.example.main.model.Message;

import java.util.List;

import androidx.lifecycle.MutableLiveData;


public interface IMessageRepository{
    public void sendMessage (Message message, MutableLiveData<String> data);
    public void receiveMessagesByUserID(String id, MutableLiveData<List<Message>> data);

}
