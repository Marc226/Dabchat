package com.example.main.di.Module;

import com.example.main.di.Scopes.DownloadScope;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.presenter.MessageListViewModel;

import java.util.concurrent.ExecutorService;

import dagger.Module;
import dagger.Provides;

@Module
public class DownloadModule {
    @Provides
    @DownloadScope
    public MessageListViewModel providesMessageListViewModel(ILoginRepository repository, IMessageRepository messageRepository, ExecutorService executorService){
        return new MessageListViewModel(messageRepository, repository,  executorService);
    }
}
