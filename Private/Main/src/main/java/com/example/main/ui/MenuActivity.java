package com.example.main.ui;

import android.os.Bundle;

import com.example.main.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import dagger.android.support.DaggerAppCompatActivity;

public class MenuActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Standard);
        setContentView(R.layout.main_screen_tabbar);
        NavController navController = Navigation.findNavController(this, R.id.menu_frag);
        setupButtonNavigation(navController);
    }

    private void setupButtonNavigation(NavController navController){
        BottomNavigationView bottomNav = findViewById(R.id.bottom_menu_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

}
