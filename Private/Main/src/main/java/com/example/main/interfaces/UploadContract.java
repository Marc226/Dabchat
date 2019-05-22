package com.example.main.interfaces;

import com.example.main.model.Message;

public interface UploadContract {

    interface iUploadPresenter extends ServiceContract.basePresenter<iUploadView>{
       public void sendMessage(Message message);
       public void receiveMessagesByUserID(String id);
    }

    interface iUploadView extends ServiceContract.baseView{
        void displayToast(String message);
    }
}
