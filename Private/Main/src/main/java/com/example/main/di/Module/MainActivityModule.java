package com.example.main.di.Module;

import android.content.Context;

import com.example.main.di.Scopes.MainActivityScope;
import com.example.main.interfaces.MainActivityController;
import com.example.main.ui.MainActivity;
import com.example.main.R;

import javax.inject.Named;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    @Provides
    @MainActivityScope
    @Named("Activity-Context")
    public Context provideMainActivityContext(MainActivity main){
        return main;
    }

    @Provides
    @MainActivityScope
    public MainActivityController provideController(MainActivity main){
        MainActivityController mainActivityController = main;
        return mainActivityController;
    }
}
