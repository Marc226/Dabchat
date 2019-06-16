package com.example.main.di.Module;


import com.example.main.background.PollNewMessagesService;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {UploadModule.class})
public abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract PollNewMessagesService providePollService();

}
