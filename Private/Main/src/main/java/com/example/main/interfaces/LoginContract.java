package com.example.main.interfaces;


public interface LoginContract {
    interface iLoginPresenter extends ServiceContract.basePresenter<iLoginView>{
        void Login(String username, String password);
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
