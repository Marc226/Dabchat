package com.example.main.presenter;

import com.example.main.interfaces.IMessageRepository;
import com.example.main.interfaces.UploadContract;
import com.example.main.model.Message;
import com.example.main.observer.UploadObserverListener;

import java.util.List;


public class UploadPresenter extends UploadObserverListener implements UploadContract.iUploadPresenter{




    private IMessageRepository repository;
    private UploadContract.iUploadView view;

    public UploadPresenter(IMessageRepository repository){
        this.repository = repository;
    }


    @Override
    public void sendMessage(Message message) {
        repository.sendMessage(message);
    }

    @Override
    public void receiveMessagesByUserID(String id) { repository.receiveMessagesByUserID(id); }


    public void dataChanged(List<Message> messages, boolean success){
        super.dataChanged(messages, success);

        if(success){

            for (Message message: messages
                 ) {
                view.displayToast(message.getId());
            }


        }


    }

    @Override
    public void onAttach(UploadContract.iUploadView view) {
        this.view = view;
        repository.subscribeObserver(this);
    }

    @Override
    public void onDetach() {
        this.view = null;
        repository.removeObserver(this);
    }
}
