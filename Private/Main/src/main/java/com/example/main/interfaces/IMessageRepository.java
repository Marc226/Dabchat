package com.example.main.interfaces;

import com.example.main.model.Message;
import com.example.main.observer.UploadObserver;

import androidx.lifecycle.MutableLiveData;


public interface IMessageRepository extends UploadObserver {
    public void sendMessage (Message message, MutableLiveData<String> data);
    public void receiveMessagesByUserID(String id);

}
