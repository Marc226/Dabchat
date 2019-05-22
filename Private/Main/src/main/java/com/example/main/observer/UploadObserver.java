package com.example.main.observer;

import com.example.main.model.Message;

import java.util.List;

public interface UploadObserver {
    void subscribeObserver(UploadObserverListener obs);
    void removeObserver(UploadObserverListener obs);
    void notifyObserver(List<Message> messages, boolean success);

}
