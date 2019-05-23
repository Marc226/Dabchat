package com.example.main.ui;

import android.os.Bundle;
import android.view.View;


import com.example.main.R;
import com.example.main.interfaces.MainActivityController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainActivityController {

    NavController navController;

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        setTheme(R.style.AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Standard);
        setContentView(R.layout.main_screen_tabbar);
        navController = Navigation.findNavController(this, R.id.main_nav);
        setupButtonNavigation();
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


}
