package com.example.main.ServiceImpl;


import android.util.Log;

import com.example.main.dao.UserDatabase;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.model.LoginForm;
import com.example.main.model.NetworkResponse;
import com.example.main.model.User;
import com.example.main.webservice.LoginWebService;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

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

    LoginWebService webservice;
    UserDatabase database;
    ExecutorService executor;

    public LoginRepository(Retrofit retrofit, UserDatabase database, ExecutorService executor){
        this.webservice = retrofit.create(LoginWebService.class);
        this.database = database;
        this.executor = executor;
    }

    @Override
    public void registerUser(User user, MutableLiveData<NetworkResponse> data) {
        Call<User> call = webservice.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    data.setValue(NetworkResponse.SUCCESS);
                    Log.d(TAG, "user register");
                } else {
                    data.setValue(NetworkResponse.FAIL);
                    Log.d(TAG, "user register failed in backend");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                data.setValue(NetworkResponse.UNAVAILABLE);
                Log.d(TAG, "register service called, but not available");
            }
        });
    }

    @Override
    public void closeDB() {
        database.close();
    }

    @Override
    public LiveData<NetworkResponse> Login(LoginForm form) {
        MutableLiveData<NetworkResponse> message = new MutableLiveData<>();
        Call<User> call = webservice.loginUser(form);
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, final retrofit2.Response<User> response) {
                if(!response.isSuccessful()){
                    message.setValue(NetworkResponse.FAIL);
                    Log.d(TAG, "Login attempt failed, at " + call.request().url());
                } else {
                    message.setValue(NetworkResponse.SUCCESS);
                    executor.submit(() -> {
                        database.userDao().dropTable();
                        database.userDao().insertCurrentUser(response.body());
                    });
                    Log.d(TAG, "Login attempt successful");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                message.setValue(NetworkResponse.UNAVAILABLE);
                Log.d(TAG, t.getMessage());
            }
        });
        return message;
    }

    @Override
    public void Logout() {
        Log.d(TAG,"user logged out");
        executor.submit(()->{
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




}
