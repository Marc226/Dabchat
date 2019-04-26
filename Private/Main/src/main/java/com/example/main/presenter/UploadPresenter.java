package com.example.main.presenter;

import com.example.main.interfaces.IMessageRepository;
import com.example.main.interfaces.UploadContract;
import com.example.main.model.Message;

import javax.inject.Inject;


public class UploadPresenter implements UploadContract.iUploadPresenter{


    private IMessageRepository repository;

    public UploadPresenter(IMessageRepository repository){
        this.repository = repository;
    }


    @Override
    public void sendMessage(Message message) {
        repository.sendMessage(message);
    }

    @Override
    public void onAttach(UploadContract.iUploadView view) {

    }

    @Override
    public void onDetach() {

    }
}
