package com.example.main.ServiceImpl;

import com.example.main.dao.FriendDatabase;
import com.example.main.interfaces.IFriendListRepository;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class FriendListRepository implements IFriendListRepository {

    private Retrofit retrofit;
    private FriendDatabase database;

    @Inject
    public FriendListRepository(Retrofit retrofit, FriendDatabase database) {
        this.retrofit = retrofit;
        this.database = database;
    }
}
