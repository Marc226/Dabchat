package com.example.main.viewmodel;

import com.example.main.interfaces.ILoginRepository;
import com.example.main.model.LoginForm;
import com.example.main.model.NetworkResponse;
import com.example.main.model.User;
import com.example.main.interfaces.LoginContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel implements LoginContract.iLoginViewModel {

    ILoginRepository repository;

    public LoginViewModel(ILoginRepository repository){
        this.repository = repository;
    }

    public LiveData<User> getCurrentUser(){
        return repository.autoLogin();
    }

    @Override
    public void closeDB() {
        repository.closeDB();
    }


    @Override
    public LiveData<NetworkResponse> Login(String username, String password) {
        LoginForm form = new LoginForm(username, password);
        return repository.Login(form);
    }

    @Override
    protected void onCleared() {
        closeDB();
        super.onCleared();
    }


}
