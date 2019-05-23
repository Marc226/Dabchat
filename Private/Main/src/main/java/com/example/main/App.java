package com.example.main;


import android.app.Activity;
import android.app.Service;

import com.example.main.di.Component.AppComponent;
import com.example.main.di.Component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.DaggerApplication;


public class App extends DaggerApplication {

    private AppComponent app;



    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        app = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        return app;
    }

}
