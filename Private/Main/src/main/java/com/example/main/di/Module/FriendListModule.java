package com.example.main.di.Module;

import android.content.Context;

import com.example.main.ServiceImpl.FriendListRepository;
import com.example.main.dao.FriendDatabase;
import com.example.main.di.Scopes.FriendListScope;
import com.example.main.interfaces.IFriendListRepository;

import java.util.concurrent.Executor;

import javax.inject.Named;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FriendListModule {

    @Provides
    @FriendListScope
    public FriendDatabase provideFriendDatabase(@Named("App-Context") Context context){
        return Room.inMemoryDatabaseBuilder(context, FriendDatabase.class).build();
    }

    @Provides
    @FriendListScope
    public IFriendListRepository provideFriendListRepository(Retrofit retrofit, FriendDatabase database, Executor executor){
        IFriendListRepository repository = new FriendListRepository(retrofit, database, executor);
        return repository;
    }
}
