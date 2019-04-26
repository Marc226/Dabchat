package com.example.main.interfaces;

import com.example.main.model.Message;

public interface UploadContract {

    interface iUploadPresenter extends ServiceContract.basePresenter<iUploadView>{
       public void sendMessage(Message message);
    }

    interface iUploadView extends ServiceContract.baseView{

    }
}
