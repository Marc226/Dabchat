package com.example.main.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.interfaces.LoginContract;

import javax.inject.Inject;


public class LoginFragment extends DaggerFragment implements LoginContract.iLoginView {

    @Inject LoginContract.iLoginPresenter presenter;

    EditText username;
    EditText password;
    Button login;
    Button register;
    ProgressBar pbar;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.Login(username.getText().toString(), password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onAttach(this);
    }


    private void initUI(){
        password = getView().findViewById(R.id.passwordLogin);
        username = getView().findViewById(R.id.usernameLogin);
        login = getView().findViewById(R.id.loginbutton);
        register = getView().findViewById(R.id.loginRegisterBut);
        pbar = getView().findViewById(R.id.loginProgress);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onDetach();
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableButtonClick(boolean bool) {
        login.setEnabled(bool);
    }

    @Override
    public void showInProgress() {
        pbar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void stopInProgress() {
        pbar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void LoginRequestFinished() {
        Navigation.findNavController(this.getView()).navigate(R.id.action_loginFragment_to_upload_fragment2);
    }
}