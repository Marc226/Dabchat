package com.example.main.di.Module;

import android.content.Context;


import com.example.main.di.Scopes.AppScope;
import com.example.main.utils.ApplicationConfig;
import com.example.main.App;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @AppScope
    public ApplicationConfig providesConfig(){

        return new ApplicationConfig();
    }

    @Provides
    @AppScope
    @Named("App-Context")
    public Context provideContext(App app){
        return app;
    }

    @Provides
    @AppScope
    public Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }


}
