package com.example.main.di.Module;

import com.example.main.di.Scopes.DownloadScope;
import com.example.main.di.Scopes.LoginScope;
import com.example.main.di.Scopes.UploadScope;
import com.example.main.ui.DownloadFragment;
import com.example.main.ui.LoginFragment;
import com.example.main.ui.PopupFragment;
import com.example.main.ui.RegisterFragment;
import com.example.main.ui.UploadFragment;

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

    @ContributesAndroidInjector(modules = {UploadModule.class})
    @UploadScope
    abstract UploadFragment bindUploadFragment();

    @ContributesAndroidInjector(modules = {DownloadModule.class})
    @DownloadScope
    abstract DownloadFragment bindDownloadFragment();

    @ContributesAndroidInjector(modules = {DownloadModule.class})
    @DownloadScope
    abstract PopupFragment bindPopupFragment();



}
