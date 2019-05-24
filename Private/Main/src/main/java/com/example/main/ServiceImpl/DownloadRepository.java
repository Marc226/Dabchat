package com.example.main.ServiceImpl;

import android.util.Log;

import com.example.main.interfaces.IDownloadRepository;
import com.example.main.model.Message;
import com.example.main.webservice.DownloadWebService;
import com.example.main.webservice.MessageWebService;

import java.util.List;
import java.util.concurrent.Executor;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadRepository /*implements IDownloadRepository*/ {

    private final String TAG = "message repository";
    MessageWebService webService;

    private Executor executor;

    public DownloadRepository(Retrofit retrofit, Executor executor){
        this.webService = retrofit.create(MessageWebService.class);
        this.executor = executor;
    }

    /*@Override
    public void receiveMessagesByUserID(String id, MutableLiveData<String> data) {
        Call<List<Message>> call = webService.receiveMessages(id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful()){
                    data.setValue(response.message());
                } else {
                    data.setValue("loading failed!");
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                data.setValue("loading failed!");
                Log.d(TAG, "tried to receive a list of messages, but it did not work\n"+t);

            }
        });
    }*/
}
