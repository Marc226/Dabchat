package com.example.main.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import dagger.android.support.DaggerFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.interfaces.RegisterContract;
import com.example.main.model.NetworkResponse;
import com.example.main.model.User;

import javax.inject.Inject;

public class RegisterFragment extends DaggerFragment {

    @Inject
    RegisterContract.iRegisterViewModel viewModel;

    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText password_repeat;
    private Button registerPageButton;
    private ProgressBar registerProgressBar;

    public RegisterFragment(){

    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    private void initUI(){
        email = getView().findViewById(R.id.register_e_mail);
        phone = getView().findViewById(R.id.register_phone);
        password = getView().findViewById(R.id.register_pw);
        password_repeat = getView().findViewById(R.id.register_repeat_pw);
        registerPageButton = getView().findViewById(R.id.registerpage_but);
        registerProgressBar = getView().findViewById(R.id.registerProgressBar);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        setOnClickListener();
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    public void displayToast(String message) {
        final Toast currentToast = Toast.makeText(this.getContext(), null, Toast.LENGTH_SHORT);
        currentToast.setText(message);
        currentToast.show();
    }

    public void enableButtonClick(boolean bool) {
        registerPageButton.setEnabled(bool);
    }

    public void showInProgress() {
        registerProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    public void stopInProgress() {
        registerProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }


    public void RegisterRequestFinished() {
        Navigation.findNavController(getActivity(), R.id.main_nav).navigate(R.id.action_registerFragment_to_loginFragment);
    }

    public void setOnClickListener(){
        registerPageButton.setOnClickListener(v -> {
            if(validateForm()){
                User user = new User(password.getText().toString(), email.getText().toString(), viewModel.checkPhoneNumber(phone.getText().toString()));
                showInProgress();
                enableButtonClick(false);
                viewModel.register(user).observe(getViewLifecycleOwner(), networkResponse -> {
                    stopInProgress();
                    enableButtonClick(true);
                    if(networkResponse == NetworkResponse.SUCCESS ){
                        displayToast(getString(R.string.registerSuccess));
                        RegisterRequestFinished();
                    } else if (networkResponse == NetworkResponse.FAIL){
                        displayToast(getString(R.string.failedRegister));
                    } else {
                        displayToast(getString(R.string.ServiceUnavailable));
                    }
                });
            }
        });
    }

    public boolean validateForm(){
        if(!viewModel.validMail(email.getText().toString()) ||
                !viewModel.checkPasswordMatch(password.getText().toString(), password_repeat.getText().toString()) ||
                viewModel.checkPhoneNumber(phone.getText().toString()) == 0){
            return false;
        }
        if(viewModel.stringsEmptyOrNull(email.getText().toString(), password.getText().toString(), password_repeat.getText().toString(), phone.getText().toString())) {
            return false;
        }
        return true;
    }
}
