package com.example.main.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
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
import com.example.main.interfaces.MainActivityController;
import com.example.main.model.User;

import javax.inject.Inject;


public class LoginFragment extends DaggerFragment implements LoginContract.iLoginView {

    @Inject
    LoginContract.iLoginViewModel viewModel;
    @Inject
    MainActivityController mainActivityController;

    EditText username;
    EditText password;
    Button login;
    Button register;
    ProgressBar pbar;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI();
        viewModel.getCurrentUser().observe(this, user -> {
            if(user != null) {
                enableButtonClick(true);
                stopInProgress();
                LoginRequestFinished();
            }
        });
        mainActivityController.hideNavBar();
        login.setOnClickListener(v -> {
            showInProgress();
            viewModel.Login(username.getText().toString(), password.getText().toString()).observe(getViewLifecycleOwner(), s -> {
                stopInProgress();
                displayToast(s);
            });
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
        mainActivityController.navigateToUpload();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.closeDB();
    }
}
