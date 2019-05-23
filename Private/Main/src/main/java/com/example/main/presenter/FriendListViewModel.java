package com.example.main.presenter;

import com.example.main.interfaces.IFriendListRepository;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.Message;
import com.example.main.model.User;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import dagger.Component;


public class FriendListViewModel extends ViewModel {


    IFriendListRepository friendListRepository;

    ILoginRepository loginRepository;

    IMessageRepository messageRepository;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private MutableLiveData<String> sendMes;
    private User currentUser;

    public FriendListViewModel(IFriendListRepository rep, ILoginRepository i, IMessageRepository m) {
        friendListRepository=rep;
        loginRepository=i;
        messageRepository=m;
        executor.submit(()->{
            currentUser = loginRepository.getLoggedInUser();
        });

    }

    public LiveData<String> addFriend(String mail) {
        MutableLiveData<String> friend = new MutableLiveData();
        executor.submit(()->{
            friendListRepository.addFriend(currentUser, mail, friend);
        });
        return friend;
    }

    public LiveData<List<User>> getFriendList(){
        return friendListRepository.getAllFriends(currentUser);
    }

    
    public LiveData<String> sendMessage(Message message){
        sendMes = new MutableLiveData<>();
        uploadMessage(message);
        return sendMes;
    }

    void uploadMessage(Message message){
        executor.submit(() ->{
            message.setFromUser(this.loginRepository.getLoggedInUser());
            messageRepository.sendMessage(message, sendMes);
        });
    }


}
