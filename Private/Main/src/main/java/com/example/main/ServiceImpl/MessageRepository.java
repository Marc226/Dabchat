package com.example.main.ServiceImpl;

import android.util.Log;

import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.Message;
import com.example.main.observer.ObserverTag;
import com.example.main.webservice.MessageWebService;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MessageRepository implements IMessageRepository {

    private final String TAG = "message repository";
    MessageWebService webService;

    private Executor executor;

    public MessageRepository(Retrofit retrofit, Executor executor){
        this.webService = retrofit.create(MessageWebService.class);
        this.executor = executor;

    }


    @Override
    public void sendMessage(Message message) {
        Call<Message> call = webService.sendMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()){

                } else {

                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d(TAG, "message service called, but not available");
            }
        });
    }
}
