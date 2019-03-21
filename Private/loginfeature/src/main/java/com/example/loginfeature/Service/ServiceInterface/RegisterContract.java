package com.example.loginfeature.Service.ServiceInterface;

import com.example.common.Common.Interface.ServiceContract;
import com.example.common.Common.model.User;

public interface RegisterContract {
    interface iRegisterPresenter extends ServiceContract.basePresenter<iRegisterView>{
        void register(User user);
        boolean checkPasswordMatch(String pw, String repeat_pw);
        int checkPhoneNumber(String phonenumber);
        boolean validMail(String mail);
    }

    interface iRegisterView extends ServiceContract.baseView{
        void displayToast(String message);
        void enableButtonClick(boolean bool);
        void showInProgress();
        void stopInProgress();
        void RegisterRequestFinished();
    }
}
