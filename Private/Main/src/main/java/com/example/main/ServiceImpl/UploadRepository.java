package com.example.main.ServiceImpl;

import android.util.Log;

import com.example.main.interfaces.IUploadRepository;
import com.example.main.model.Message;
import com.example.main.webservice.UploadWebService;

import java.util.concurrent.Executor;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadRepository implements IUploadRepository {
    private final String TAG = "message repository";
    UploadWebService webService;

    private Executor executor;

    public UploadRepository(Retrofit retrofit, Executor executor){
        this.webService = retrofit.create(UploadWebService.class);
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
}
