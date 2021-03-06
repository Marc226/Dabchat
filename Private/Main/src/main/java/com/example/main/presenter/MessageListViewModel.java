package com.example.main.presenter;

import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.Message;
import com.example.main.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MessageListViewModel extends ViewModel {
    IMessageRepository messageRepository;
    ILoginRepository loginRepository;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private MutableLiveData<List<Message>> receivedListOfMessages = new MutableLiveData<>();
    private User currentUser;

    public MessageListViewModel(IMessageRepository messageRepository, ILoginRepository loginRepository){
        this.messageRepository = messageRepository;
        this.loginRepository = loginRepository;

        executor.submit(()->{
            currentUser = loginRepository.getLoggedInUser();
        });

    }


    public LiveData<List<Message>> downloadMessages(){
        executor.submit(() ->{
            messageRepository.receiveMessagesByUserID(currentUser.getId(), receivedListOfMessages);
        });

        return receivedListOfMessages;
    }

    public void removeRecipent(Message currentMessage){
        messageRepository.removeUserFromRecipients(currentMessage);
    }

    public void closeDB(){
        loginRepository.closeDB();
    }

}
