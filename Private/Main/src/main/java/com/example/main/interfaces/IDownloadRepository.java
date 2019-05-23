package com.example.main.interfaces;

import androidx.lifecycle.MutableLiveData;

public interface IDownloadRepository {
    public void receiveMessagesByUserID(String id, MutableLiveData<String> data);

}
