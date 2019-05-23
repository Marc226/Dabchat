package com.example.main.ui;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.main.R;
import com.example.main.background.PollNewMessagesService;
import com.example.main.interfaces.MainActivityController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainActivityController, HasActivityInjector, HasServiceInjector {

    NavController navController;

    BottomNavigationView bottomNav;
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        setTheme(R.style.AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Standard);
        setContentView(R.layout.main_screen_tabbar);
        navController = Navigation.findNavController(this, R.id.main_nav);
        setupButtonNavigation();

        //Start Background Service
        Intent startPollService = new Intent(MainActivity.this, PollNewMessagesService.class);
        startService(startPollService);
    }


    private void setupButtonNavigation(){
        bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);
        bottomNav.setVisibility(View.INVISIBLE);
    }

    public void hideNavBar(){
        bottomNav.setVisibility(View.INVISIBLE);
    }

    public void showNavBar(){
        bottomNav.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToUpload() {
        navController.navigate(R.id.action_loginFragment_to_uploadFragment);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return this.activityInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return this.serviceInjector;
    }
}
