package com.example.loginfeature.di;

import androidx.room.Room;
import android.content.Context;

import com.example.loginfeature.Service.Presenter.LoginPresenter;
import com.example.loginfeature.Service.Presenter.RegisterPresenter;
import com.example.loginfeature.Service.ServiceImpl.LoginRepository;
import com.example.common.Common.Interface.ILoginRepository;
import com.example.loginfeature.Service.ServiceInterface.LoginContract;
import com.example.loginfeature.Service.ServiceInterface.RegisterContract;
import com.example.loginfeature.dao.UserDatabase;

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
        return Room.databaseBuilder(context, UserDatabase.class, "database-name").build();
    }

    @Provides
    @LoginScope
    public LoginContract.iLoginPresenter providePresenter(ILoginRepository repository){
        LoginContract.iLoginPresenter presenter = new LoginPresenter(repository);
        return presenter;
    }

    @Provides
    @LoginScope
    public ILoginRepository providerepository(Retrofit retrofit, UserDatabase database, Executor exec){
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
