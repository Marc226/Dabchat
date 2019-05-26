package com.example.main.webservice;

import com.example.main.model.LoginForm;
import com.example.main.model.Message;
import com.example.main.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageWebService  {
    @POST("send/upload")
    Call<Message> sendMessage (@Body Message message);
    @POST("send/get_pending")
    Call<List<Message>> receiveMessages (@Body String id);
    @POST("send/user_has_messages")
    Call<List<User>> userHasMessage (@Body LoginForm form);


}
