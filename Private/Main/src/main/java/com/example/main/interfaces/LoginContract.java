package com.example.main.interfaces;


import com.example.main.model.NetworkResponse;
import com.example.main.model.User;

import androidx.lifecycle.LiveData;

public interface LoginContract {
    interface iLoginViewModel{
        LiveData<NetworkResponse> Login(String username, String password);
        LiveData<User> getCurrentUser();
        void closeDB();
    }
}
