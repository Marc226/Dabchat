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

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class FriendListViewModel extends ViewModel {

    @Inject
    IFriendListRepository friendListRepository;

    @Inject
    ILoginRepository loginRepository;

    @Inject
    IMessageRepository messageRepository;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private MutableLiveData<List<User>> friends;
    private MutableLiveData<String> sendMes;

    public LiveData<List<User>> getFriendList(){
        if(friends == null){
            friends = new MutableLiveData<>();
            loadFriends();
        }
        return friends;
    }

    public LiveData<String> sendMessage(Message message){
        sendMes = new MutableLiveData<>();
        uploadMessage(message);
        return sendMes;
    }

    void loadFriends(){
        executor.submit(()->{
            User currentUser = loginRepository.getLoggedInUser();
            friendListRepository.getAllFriends(currentUser, friends);
        });
    }

    void uploadMessage(Message message){
        executor.submit(() ->{
           messageRepository.sendMessage(message, sendMes);
        });
    }


}
