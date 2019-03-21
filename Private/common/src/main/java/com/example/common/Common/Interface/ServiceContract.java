package com.example.common.Common.Interface;

public interface ServiceContract {
    interface baseView {
    }

    interface basePresenter<T> {
        void onAttach(T view);
        void onDetach();
    }
}
