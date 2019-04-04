package com.example.main.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

public class ActivityUtils {

    @Inject
    public ActivityUtils(){

    }

    public static void addFragmentToActivity (FragmentManager fragmentManager, Fragment fragment, int frameId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

}
