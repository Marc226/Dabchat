package com.example.main.webservice;

import com.example.main.model.Message;

import retrofit2.Call;
import retrofit2.http.POST;

public interface MessageWebService  {
    @POST("send/upload")
    Call<Message> sendMessage (Message message);



}
