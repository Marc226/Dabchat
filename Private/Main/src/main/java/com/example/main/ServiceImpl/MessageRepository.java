package com.example.main.ServiceImpl;

import android.util.Log;

import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.Message;
import com.example.main.webservice.MessageWebService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class MessageRepository implements IMessageRepository {

    private final String TAG = "message repository";
    MessageWebService webService;

    private Executor executor;

    public MessageRepository(Retrofit retrofit, Executor executor){
        this.webService = retrofit.create(MessageWebService.class);
        this.executor = executor;
    }


    @Override
    public void sendMessage(Message message, MutableLiveData<String> data) {

        Call<Message> call = webService.sendMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if(response.isSuccessful()){
                    data.setValue("Upload successful");
                } else {
                    data.setValue("Upload failed");
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d(TAG, "message service called, but not available\n"+t);
                data.setValue("Service unavailable!");
            }
        });
    }

    @Override
    public void receiveMessagesByUserID(String id, MutableLiveData<List<Message>> data) {
        Call<List<Message>> call = webService.receiveMessages(id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body());
                } else {
                    Log.d(TAG,"Could not find list");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

                Log.d(TAG, "tried to receive a list of messages, but it did not work\n"+t);

            }
        });
    }



}
