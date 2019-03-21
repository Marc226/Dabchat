package com.example.common.Common.webservice;

import com.example.common.Common.model.LoginForm;
import com.example.common.Common.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginWebService {
    @POST("login/loginUser")
    Call<User> loginUser(@Body LoginForm loginForm);

    @POST("login/register")
    Call<User> registerUser(@Body User user);
}
