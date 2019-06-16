package com.example.main.presenter;

import android.util.Patterns;

import com.example.main.model.NetworkResponse;
import com.example.main.model.User;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.RegisterContract;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class RegisterViewModel extends ViewModel implements RegisterContract.iRegisterViewModel {


    ILoginRepository repository;
    ExecutorService executor;

    public RegisterViewModel(ILoginRepository repository, ExecutorService executor){
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public LiveData<NetworkResponse> register(User user) {
        MutableLiveData<NetworkResponse> data = new MutableLiveData<>();
        executor.submit(()-> {
            repository.registerUser(user, data);
        });
        return data;
    }

    @Override
    public boolean stringsEmptyOrNull(String... strings) {
        for(String str : strings) {
            if(str == null || str.equals("")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkPasswordMatch(String pw, String repeat_pw) {
        if(pw.contentEquals(repeat_pw)){
            return true;
        }
        return false;
    }

    @Override
    public int checkPhoneNumber(String phonenumber) {
        try{
            int phone = Integer.parseInt(phonenumber);
            return phone;
        } catch(NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public boolean validMail(String mail) {
        if(Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            return true;
        }
        return false;
    }


}
