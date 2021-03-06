package com.example.main.di.Module;

import androidx.room.Room;
import android.content.Context;

import com.example.main.dao.UserDatabase;
import com.example.main.di.Scopes.LoginScope;
import com.example.main.presenter.LoginViewModel;
import com.example.main.presenter.RegisterPresenter;
import com.example.main.ServiceImpl.LoginRepository;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.LoginContract;
import com.example.main.interfaces.RegisterContract;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class LoginModule {
    @Provides
    @LoginScope
    public UserDatabase provideUserDatabase(@Named("App-Context")Context context){
        return Room.databaseBuilder(context, UserDatabase.class, "login").build();
    }

    @Provides
    @LoginScope
    public LoginContract.iLoginViewModel provideLoginViewModel(ILoginRepository repository){
        LoginContract.iLoginViewModel presenter = new LoginViewModel(repository);
        return presenter;
    }

    @Provides
    @LoginScope
    public ILoginRepository provideRepository(Retrofit retrofit, UserDatabase database, Executor exec){
        ILoginRepository repo = new LoginRepository(retrofit, database, exec);
        return repo;
    }



    @Provides
    @LoginScope
    public RegisterContract.iRegisterPresenter provideRegisterPresenter(ILoginRepository repository){
        RegisterContract.iRegisterPresenter presenter = new RegisterPresenter(repository);
        return presenter;
    }

}
