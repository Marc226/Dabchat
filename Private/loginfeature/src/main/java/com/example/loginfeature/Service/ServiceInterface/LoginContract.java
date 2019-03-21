package com.example.loginfeature.Service.ServiceInterface;


import com.example.common.Common.Interface.ServiceContract;

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
