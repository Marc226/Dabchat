package com.example.main.interfaces;

import com.example.main.model.NetworkResponse;
import com.example.main.model.User;

import androidx.lifecycle.LiveData;

public interface RegisterContract {
    interface iRegisterViewModel {
        LiveData<NetworkResponse> register(User user);
        boolean checkPasswordMatch(String pw, String repeat_pw);
        int checkPhoneNumber(String phonenumber);
        boolean validMail(String mail);
        boolean stringsEmptyOrNull(String... strings);
    }
}
