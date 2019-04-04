package com.example.main.presenter;

import com.example.main.interfaces.ILoginRepository;
import com.example.main.model.LoginForm;
import com.example.main.observer.LoginObserverListener;
import com.example.main.observer.ObserverTag;
import com.example.main.interfaces.LoginContract;

public class LoginPresenter extends LoginObserverListener implements LoginContract.iLoginPresenter {

    private ObserverTag ObsTag = ObserverTag.LOGIN;

    ILoginRepository repository;
    LoginContract.iLoginView view;


    public LoginPresenter(ILoginRepository repository){
        this.repository = repository;
    }




    @Override
    public void Login(String username, String password) {
        this.view.showInProgress();
        this.view.enableButtonClick(false);
        LoginForm form = new LoginForm(username, password);
        repository.Login(form);

    }

    @Override
    public void Logout() {

    }

    @Override
    public void dataChanged(String message, boolean success) {
        super.dataChanged(message, success);
        this.view.stopInProgress();
        this.view.enableButtonClick(true);
        if(!success){
            view.displayToast(message);
        } else {
            view.displayToast(message);
            view.LoginRequestFinished();
        }
    }

    @Override public int getObserverTag() {
        return ObsTag.getIntvalue();
    }

    @Override
    public void onAttach(LoginContract.iLoginView view) {
        this.view = view;
        repository.subscribeObserver(this);
    }

    @Override
    public void onDetach() {
        this.view = null;
        repository.removeObserver(this);
    }
}
