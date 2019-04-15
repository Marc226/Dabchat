package com.example.main.di.Module;


import com.example.main.di.Scopes.MenuActivityScope;
import com.example.main.ui.MainActivity;
import com.example.main.di.Scopes.MainActivityScope;
import com.example.main.ui.MenuActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppActivityModule {
    @ContributesAndroidInjector(modules = {MainActivityModule.class, FragmentModule.class})
    @MainActivityScope
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {MenuActivityModule.class, FragmentModule.class})
    @MenuActivityScope
    abstract MenuActivity bindMenuActivity();


}
