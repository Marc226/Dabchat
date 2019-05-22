package com.example.main.ServiceImpl;

import android.util.Log;

import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.Message;
import com.example.main.observer.LoginObserverListener;
import com.example.main.observer.ObserverTag;
import com.example.main.observer.UploadObserverListener;
import com.example.main.webservice.MessageWebService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class MessageRepository implements IMessageRepository {

    private final String TAG = "message repository";
    MessageWebService webService;

    private Executor executor;
    private ArrayList<UploadObserverListener> observerListeners;

    public MessageRepository(Retrofit retrofit, Executor executor){
        this.webService = retrofit.create(MessageWebService.class);
        this.executor = executor;
        this.observerListeners = new ArrayList<>();
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
                Log.d(TAG, "message service called, but not available\n"+t);

            }
        });
    }

    @Override
    public void receiveMessagesByUserID(String id) {
        Call<List<Message>> call = webService.receiveMessages(id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful()){
                    notifyObserver(response.body(), true);
                } else {
                    notifyObserver(null, false);
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d(TAG, "tried to receive a list of messages, but it did not work\n"+t);

            }
        });
    }


    @Override
    public void subscribeObserver(UploadObserverListener obs) {
        observerListeners.add(obs);
    }

    @Override
    public void removeObserver(UploadObserverListener obs) {
        observerListeners.remove(obs);
    }


    @Override
    public void notifyObserver(List<Message> messages, boolean success) {
        for(UploadObserverListener obs : observerListeners){

                obs.dataChanged(messages, success);

        }
    }
}
