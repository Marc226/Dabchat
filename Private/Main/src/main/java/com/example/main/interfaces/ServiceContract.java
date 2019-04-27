package com.example.main.interfaces;

public interface ServiceContract {
    interface baseView {
    }

    interface basePresenter<T> {
        void onAttach(T view);
        void onDetach();
    }
}
