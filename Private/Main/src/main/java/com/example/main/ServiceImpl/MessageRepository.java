package com.example.main.ServiceImpl;

import android.app.Activity;
import android.util.Log;

import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.LoginForm;
import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.webservice.MessageWebService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class MessageRepository implements IMessageRepository {

    private final String TAG = "message repository";
    private final ILoginRepository loginRepository;
    MessageWebService webService;

    private Executor executor;
    private List<User> pendingFromUsers;

    public MessageRepository(Retrofit retrofit, ILoginRepository loginRepository, Executor executor){
        this.webService = retrofit.create(MessageWebService.class);
        this.executor = executor;
        this.loginRepository = loginRepository;
        pendingFromUsers = new ArrayList();
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

    @Override
    public void clearPending() {
        this.pendingFromUsers.clear();
    }

    @Override
    public void receiveFriendsWithPendingMessages(String id, MutableLiveData<List<User>> data) {
        User user = loginRepository.getLoggedInUser();
        if(user == null) return;
        Call<List<User>> call = webService.userHasMessage(new LoginForm(user.getMail(), user.getPassWord()));
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    addMissingFromUsers(response.body());
                    data.setValue(response.body());
                } else {
                    data.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                data.setValue(null);
                Log.d(TAG, "tried to receive a list of messages, but it did not work\n"+t);

            }
        });
    }

    private void addMissingFromUsers(List<User> users) {
        for(User user : users) {
            if(pendingFromUsers.contains(user)) continue;

            pendingFromUsers.add(user);
        }
    }

    public List<User> getPendingFromUsers() {
        return pendingFromUsers;
    }
}
