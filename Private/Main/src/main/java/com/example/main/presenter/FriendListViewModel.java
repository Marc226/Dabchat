package com.example.main.presenter;

import com.example.main.model.User;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendListViewModel extends ViewModel {


    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private MutableLiveData<List<User>> friends;

    public LiveData<List<User>> getFriendList(){
        if(friends == null){
            friends = new MutableLiveData<>();
        }
        return friends;
    }

    void loadFriends(){
        executor.submit(()->{

        });
    }
}
