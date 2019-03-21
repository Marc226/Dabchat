package com.example.common.Common.Interface;

import com.example.common.Common.model.LoginForm;
import com.example.common.Common.model.User;
import com.example.common.Common.observer.LoginObserver;

import java.util.List;
import java.util.Observer;

import retrofit2.Response;

public interface ILoginRepository extends LoginObserver {
    void Login(LoginForm form);
    void Logout();
    User getLoggedInUser();
    void registerUser(User user);
}
