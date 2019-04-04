package com.example.main.interfaces;


import com.example.main.model.LoginForm;
import com.example.main.model.User;
import com.example.main.observer.LoginObserver;

public interface ILoginRepository extends LoginObserver {
    void Login(LoginForm form);
    void Logout();
    User getLoggedInUser();
    void registerUser(User user);
}
