package com.example.main.observer;

public abstract class LoginObserverListener {

    public int ObsTag = ObserverTag.DEFAULT.getIntvalue();

    public int getObserverTag() {
        return ObsTag;
    }

    public void dataChanged(String message, boolean success){

    }
}
