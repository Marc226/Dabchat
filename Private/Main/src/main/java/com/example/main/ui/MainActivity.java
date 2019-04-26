package com.example.main.ui;

import android.os.Bundle;


import com.example.main.R;

import androidx.navigation.Navigation;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        setTheme(R.style.AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Standard);
        setContentView(R.layout.main_content);
        Navigation.findNavController(this, R.id.main_content).navigate(R.id.action_rootFragment_to_menuFragment);
    }


}
