package com.example.main.ServiceImpl;

import android.util.Log;

import com.example.main.R;
import com.example.main.dao.FriendDatabase;
import com.example.main.interfaces.IFriendListRepository;
import com.example.main.model.User;
import com.example.main.webservice.FriendListService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class FriendListRepository implements IFriendListRepository {

    private final String TAG = "FriendList Repo";
    private FriendDatabase database;
    private FriendListService webservice;
    private Executor executor;


    public FriendListRepository(Retrofit retrofit, FriendDatabase database, Executor executor) {
        webservice = retrofit.create(FriendListService.class);
        this.database = database;
        this.executor = executor;
    }

    @Override
    public void addFriend(User user, String currentUserId, MutableLiveData<String> data) {
        Call<String> call = webservice.addFriend(user, currentUserId);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    data.setValue("Friend added!");
                } else {
                    data.setValue("Request unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                data.setValue("Service unavailable!");
            }
        });
    }

    @Override
    public LiveData<List<User>> getAllFriends(User currentUser) {
        refreshFriendList(currentUser);
        return database.friendDao().getAllFriends();
    }

    @Override
    public LiveData<User> getSpecificFriend(String id) {
        return database.friendDao().getFriend(id);
    }

    @Override
    public void closeDB() {
        database.close();
    }

    private void refreshFriendList(User currentUser){
        Call<List<User>> call = webservice.getAllFriends(currentUser);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    executor.execute(()->{
                        database.friendDao().dropTable();
                        for(User user: response.body()){
                            database.friendDao().insertFriend(user);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "service unavailable");
            }
        });
    }

}
