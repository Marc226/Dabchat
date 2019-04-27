package com.example.main.presenter;

import android.util.Patterns;

import com.example.main.model.User;
import com.example.main.observer.LoginObserverListener;
import com.example.main.observer.ObserverTag;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.RegisterContract;

public class RegisterPresenter extends LoginObserverListener implements RegisterContract.iRegisterPresenter {

    private ObserverTag ObsTag = ObserverTag.REGISTER;

    RegisterContract.iRegisterView view;
    ILoginRepository repository;

    public RegisterPresenter(ILoginRepository repository){
        this.repository = repository;
    }

    @Override
    public void register(User user) {
            view.showInProgress();
            view.enableButtonClick(false);
            repository.registerUser(user);
    }

    @Override
    public boolean checkPasswordMatch(String pw, String repeat_pw) {
        if(pw.contentEquals(repeat_pw)){
            return true;
        }
        view.displayToast("Password doesn't match");
        return false;
    }

    @Override
    public int checkPhoneNumber(String phonenumber) {
        try{
            int phone = Integer.parseInt(phonenumber);
            return phone;
        } catch(NumberFormatException e) {
            view.displayToast("Phone number invalid");
            return 0;
        }
    }

    @Override
    public boolean validMail(String mail) {
        if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            return true;
        }
        view.displayToast("Invalid mail");
        return false;
    }

    @Override
    public void onAttach(RegisterContract.iRegisterView view) {
        this.view = view;
        repository.subscribeObserver(this);
    }

    @Override
    public void onDetach() {
        this.view = null;
        repository.removeObserver(this);
    }

    @Override
    public int getObserverTag() {
        return ObsTag.getIntvalue();
    }

    @Override
    public void dataChanged(String message, boolean success) {
        super.dataChanged(message, success);
        this.view.stopInProgress();
        this.view.enableButtonClick(true);
        this.view.displayToast(message);
        if(!success){

        } else {
            this.view.RegisterRequestFinished();
        }
    }
}
