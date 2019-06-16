package com.example.main.interfaces;


import com.example.main.model.LoginForm;
import com.example.main.model.NetworkResponse;
import com.example.main.model.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface ILoginRepository {
    LiveData<NetworkResponse> Login(LoginForm form);
    void Logout();
    LiveData<User> autoLogin();
    User getLoggedInUser();
    void registerUser(User user, MutableLiveData<NetworkResponse> data);
    void closeDB();
}
