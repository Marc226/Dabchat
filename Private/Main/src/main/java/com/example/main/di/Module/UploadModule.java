package com.example.main.di.Module;

import com.example.main.ServiceImpl.MessageRepository;
import com.example.main.di.Scopes.UploadScope;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.interfaces.UploadContract;
import com.example.main.presenter.UploadPresenter;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class UploadModule {

    @Provides
    @UploadScope
    public UploadContract.iUploadPresenter provideUploadPresenter(IMessageRepository messageRepository){
        UploadContract.iUploadPresenter presenter = new UploadPresenter(messageRepository);
        return presenter;
    }

    @Provides
    @UploadScope
    public  IMessageRepository providesMessageRepository(Retrofit retrofit, Executor executor){
        IMessageRepository messageRepository = new MessageRepository(retrofit, executor);
        return messageRepository;
    }

}
