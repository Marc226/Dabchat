package com.example.main.interfaces;


import com.example.main.model.User;

import androidx.lifecycle.LiveData;

public interface LoginContract {
    interface iLoginViewModel{
        LiveData<String> Login(String username, String password);
        LiveData<User> getCurrentUser();
        void Logout();
    }

    interface iLoginView extends ServiceContract.baseView{
        void displayToast(String message);
        void enableButtonClick(boolean bool);
        void showInProgress();
        void stopInProgress();
        void LoginRequestFinished();
    }
}
