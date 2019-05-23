package com.example.main.presenter;

import com.example.main.interfaces.ILoginRepository;
import com.example.main.model.LoginForm;
import com.example.main.model.User;
import com.example.main.observer.ObserverTag;
import com.example.main.interfaces.LoginContract;

import androidx.lifecycle.LiveData;

public class LoginViewModel implements LoginContract.iLoginViewModel {

    private ObserverTag ObsTag = ObserverTag.LOGIN;

    ILoginRepository repository;


    public LoginViewModel(ILoginRepository repository){
        this.repository = repository;
    }

    public LiveData<User> getCurrentUser(){
        return repository.autoLogin();
    }


    @Override
    public LiveData<String> Login(String username, String password) {
        LoginForm form = new LoginForm(username, password);
        return repository.Login(form);
    }

    @Override
    public void Logout() {
    }
}
