package com.example.main.ServiceImpl;


import android.util.Log;

import com.example.main.dao.UserDatabase;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.model.LoginForm;
import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.observer.LoginObserverListener;
import com.example.main.observer.ObserverTag;
import com.example.main.webservice.LoginWebService;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
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
    public void closeDB() {
        database.close();
    }

    @Override
    public LiveData<String> Login(LoginForm form) {
        MutableLiveData<String> message = new MutableLiveData<>();
        Call<User> call = webservice.loginUser(form);
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, final retrofit2.Response<User> response) {
                if(!response.isSuccessful()){
                    message.setValue("Failed to login!");
                    Log.d(TAG, "Login attempt failed, at " + call.request().url());
                } else {
                    message.setValue("Login Successful!");
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            database.userDao().insertCurrentUser(response.body());
                        }
                    });
                    Log.d(TAG, "Login attempt successful");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                message.setValue("Service Unavailable");
                Log.d(TAG, t.getMessage());
            }
        });
        return message;
    }

    @Override
    public void Logout() {
        Log.d(TAG,"user logged out");
        executor.execute(()->{
            database.userDao().dropTable();
        });
    }

    @Override
    public LiveData<User> autoLogin() {
        return database.userDao().autoLogin();
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
