package com.example.loginfeature.Service.ServiceImpl;

import android.util.Log;

import com.example.common.Common.Interface.ILoginRepository;
import com.example.common.Common.observer.LoginObserverListener;
import com.example.common.Common.observer.ObserverTag;
import com.example.common.Common.webservice.LoginWebService;
import com.example.common.Common.model.LoginForm;
import com.example.common.Common.model.User;
import com.example.loginfeature.dao.UserDatabase;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class LoginRepository implements ILoginRepository {

    private final String TAG = "user repository";

    ArrayList<LoginObserverListener> observerListeners;
    LoginWebService webservice;
    UserDatabase database;
    Executor executor;

    public LoginRepository(Retrofit retrofit, UserDatabase database, Executor executor){
        this.webservice = retrofit.create(LoginWebService.class);
        this.database = database;
        this.observerListeners = new ArrayList<>();
        this.executor = executor;
    }

    @Override
    public void registerUser(User user) {
        Call<User> call = webservice.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    notifyObserver("User registered", true, ObserverTag.REGISTER);
                    Log.d(TAG, "user register");
                } else {
                    notifyObserver("Failed to register user", false, ObserverTag.REGISTER);
                    Log.d(TAG, "user register failed in backend");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                notifyObserver("Service unavailable", false, ObserverTag.REGISTER);
                Log.d(TAG, "register service called, but not available");
            }
        });
    }

    @Override
    public void Login(LoginForm form) {
        Call<User> call = webservice.loginUser(form);
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, final retrofit2.Response<User> response) {
                if(!response.isSuccessful()){
                    notifyObserver("Login failed", false, ObserverTag.LOGIN);
                    Log.d(TAG, "Login attempt failed, at " + call.request().url());
                } else {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            database.userDao().insertCurrentUser(response.body());
                        }
                    });
                    notifyObserver("Login Successful, redirecting...", true, ObserverTag.LOGIN);
                    Log.d(TAG, "Login attempt successful");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                notifyObserver("Service unavailable", false, ObserverTag.LOGIN);
            }
        });
    }

    @Override
    public void Logout() {
        Log.d(TAG,"user logged out");
        database.userDao().removeCurrentUser(database.userDao().getCurrentUser());
    }


    @Override
    public User getLoggedInUser() {
        return database.userDao().getCurrentUser();
    }

    @Override
    public void subscribeObserver(LoginObserverListener obs) {
        observerListeners.add(obs);
    }

    @Override
    public void removeObserver(LoginObserverListener obs) {
        observerListeners.remove(obs);
    }

    @Override
    public void notifyObserver(String message, boolean success, ObserverTag target) {
        for(LoginObserverListener obs : observerListeners){
            if(obs.getObserverTag() == target.getIntvalue()){
                obs.dataChanged(message, success);
            }
        }
    }
}
