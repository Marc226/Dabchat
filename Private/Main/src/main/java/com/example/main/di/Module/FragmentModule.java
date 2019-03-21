package com.example.main.di.Module;

import com.example.loginfeature.di.LoginModule;
import com.example.loginfeature.di.LoginScope;
import com.example.loginfeature.ui.LoginFragment;
import com.example.loginfeature.ui.RegisterFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector(modules = {LoginModule.class})
    @LoginScope
    abstract RegisterFragment bindRegisterFragment();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    @LoginScope
    abstract LoginFragment bingLoginFragment();

}
