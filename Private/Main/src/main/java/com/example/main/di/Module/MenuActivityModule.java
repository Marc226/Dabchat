package com.example.main.di.Module;

import android.content.Context;

import com.example.main.R;
import com.example.main.di.Scopes.MainActivityScope;
import com.example.main.ui.MainActivity;

import javax.inject.Named;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import dagger.Module;
import dagger.Provides;

@Module
public class MenuActivityModule {
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
