package com.example.main.di.Module;


import com.example.main.background.PollNewMessagesService;
import com.example.main.background.UploadService;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {UploadModule.class})
public abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract PollNewMessagesService providePollService();

    @ContributesAndroidInjector
    abstract UploadService provideUploadService();

}
