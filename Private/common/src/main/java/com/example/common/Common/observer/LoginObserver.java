package com.example.common.Common.observer;


public interface LoginObserver {
    void subscribeObserver(LoginObserverListener obs);
    void removeObserver(LoginObserverListener obs);
    void notifyObserver(String message, boolean success, ObserverTag target);
}
