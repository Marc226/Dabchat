package com.example.main;



import com.example.main.di.Component.AppComponent;
import com.example.main.di.Component.DaggerAppComponent;



import dagger.android.AndroidInjector;
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
