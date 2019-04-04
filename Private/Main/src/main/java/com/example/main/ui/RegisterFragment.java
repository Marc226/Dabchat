package com.example.main.ui;

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
import com.example.main.model.User;

import javax.inject.Inject;

public class RegisterFragment extends DaggerFragment implements RegisterContract.iRegisterView {

    @Inject RegisterContract.iRegisterPresenter presenter;

    EditText email;
    EditText phone;
    EditText password;
    EditText password_repeat;
    Button registerPageButton;
    ProgressBar registerProgressBar;

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
        initUI();
        setOnClickListener();
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onAttach(this);
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
        registerPageButton.setEnabled(bool);
    }

    @Override
    public void showInProgress() {
        registerProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void stopInProgress() {
        registerProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }


    @Override
    public void RegisterRequestFinished() {
        Navigation.findNavController(this.getView()).navigate(R.id.action_registerFragment_to_loginFragment);
    }

    public void setOnClickListener(){
        registerPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    User user = new User(password.getText().toString(), email.getText().toString(), presenter.checkPhoneNumber(phone.getText().toString()));
                    presenter.register(user);
                }
            }
        });
    }

    public boolean validateForm(){
        if(!presenter.validMail(email.getText().toString()) ||
                !presenter.checkPasswordMatch(password.getText().toString(), password_repeat.getText().toString()) ||
                presenter.checkPhoneNumber(phone.getText().toString()) == 0){
            return false;
        }
        return true;
    }
}
