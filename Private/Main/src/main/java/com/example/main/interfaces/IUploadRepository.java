package com.example.main.interfaces;

import com.example.main.model.Message;

import androidx.lifecycle.MutableLiveData;

public interface IUploadRepository {
    public void sendMessage(Message message, MutableLiveData<String> data);




}
