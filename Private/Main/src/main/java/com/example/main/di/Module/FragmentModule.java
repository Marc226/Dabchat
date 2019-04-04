package com.example.main.di.Module;

import com.example.main.di.Scopes.LoginScope;
import com.example.main.ui.LoginFragment;
import com.example.main.ui.RegisterFragment;

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
