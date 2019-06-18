package com.example.main.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.main.R;
import com.example.main.background.UploadService;
import com.example.main.interfaces.IFriendListRepository;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.LoginForm;
import com.example.main.model.Message;
import com.example.main.model.NetworkResponse;
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


    private IFriendListRepository friendListRepository;

    private ILoginRepository loginRepository;

    private IMessageRepository messageRepository;

    private ExecutorService executor;
    private User currentUser;
    private MutableLiveData<String> friend = new MutableLiveData();

    public FriendListViewModel(IFriendListRepository rep, ILoginRepository i, IMessageRepository m, ExecutorService executor) {
        friendListRepository = rep;
        loginRepository = i;
        messageRepository = m;
        this.executor = executor;
        executor.submit(()->{
            currentUser = loginRepository.getLoggedInUser();
        });

    }

    public LiveData<User> getCurrentUser(){
        return loginRepository.autoLogin();
    }

    public LiveData<NetworkResponse> login(User user){
        return loginRepository.Login(new LoginForm(user.getMail(), user.getPassWord()));
    }

    public LiveData<String> addFriend(String mail) {
        executor.submit(()->{
            friendListRepository.addFriend(currentUser, mail, friend);
        });
        return friend;
    }

    public LiveData<List<User>> getFriendList(){
        return friendListRepository.getAllFriends(currentUser);
    }

    
    public void sendMessage(Message message, Context context, Uri imageuri){
        message.setFromUser(currentUser);
        Intent intent = new Intent(context, UploadService.class);
        intent.putExtra(context.getString(R.string.messageUploadId), message);
        intent.putExtra(context.getString(R.string.uriUploadID), imageuri.toString());
        UploadService.enqueueWork(context, intent);
    }

    public void Logout(){
        loginRepository.Logout();
    }

    public void closeDB(){
        loginRepository.closeDB();
        friendListRepository.closeDB();
    }

}
