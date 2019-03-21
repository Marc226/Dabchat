package com.example.main.di.Module;

import android.content.Context;

import com.example.common.Common.di.Scopes.MainActivityScope;
import com.example.main.MainActivity;
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
    public NavController provideNavController(MainActivity main){
        return Navigation.findNavController(main, R.id.main_content);
    }
}
