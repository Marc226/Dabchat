package com.example.main.interfaces;

import com.example.main.model.Message;
import com.example.main.observer.UploadObserver;

public interface IMessageRepository extends UploadObserver {
    public void sendMessage (Message message);
    public void receiveMessagesByUserID(String id);

}
