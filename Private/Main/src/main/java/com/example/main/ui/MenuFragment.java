package com.example.main.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.main.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import dagger.android.support.DaggerFragment;

public class MenuFragment extends DaggerFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_screen_tabbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController nav = Navigation.findNavController(view);
        setupButtonNavigation(nav, view);
    }

    private void setupButtonNavigation(NavController navController, View view){
        BottomNavigationView bottomNav = view.findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

}
