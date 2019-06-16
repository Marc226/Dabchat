package com.example.main.interfaces;


import com.example.main.model.LoginForm;
import com.example.main.model.User;
import com.example.main.observer.LoginObserver;

import androidx.lifecycle.LiveData;

public interface ILoginRepository extends LoginObserver {
    LiveData<String> Login(LoginForm form);
    void Logout();
    LiveData<User> autoLogin();
    User getLoggedInUser();
    void registerUser(User user);
    void closeDB();
}
