package com.example.main.di.Module;

import android.content.Context;

import com.example.main.ServiceImpl.FriendListRepository;
import com.example.main.ServiceImpl.MessageRepository;
import com.example.main.dao.FriendDatabase;
import com.example.main.di.Scopes.LoginScope;
import com.example.main.di.Scopes.UploadScope;
import com.example.main.interfaces.IFriendListRepository;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.presenter.FriendListViewModel;

import java.util.concurrent.Executor;

import javax.inject.Named;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {LoginModule.class})
public class UploadModule {



    @Provides
    @UploadScope
    public  IMessageRepository providesMessageRepository(Retrofit retrofit, Executor executor){
        IMessageRepository messageRepository = new MessageRepository(retrofit, executor);
        return messageRepository;
    }

    @Provides
    @UploadScope
    public FriendDatabase provideFriendDatabase(@Named("App-Context") Context context){
        return Room.inMemoryDatabaseBuilder(context, FriendDatabase.class).build();
    }

    @Provides
    @UploadScope
    public IFriendListRepository provideFriendListRepository(Retrofit retrofit, FriendDatabase database, Executor executor){
        IFriendListRepository repository = new FriendListRepository(retrofit, database, executor);
        return repository;
    }

    @Provides
    @UploadScope
    public FriendListViewModel provideFriendListViewModel(IFriendListRepository rep, ILoginRepository i, IMessageRepository m){
        return new FriendListViewModel(rep, i, m);
    }

}
