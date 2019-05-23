package com.example.main.di.Component;


import com.example.main.di.Module.ServiceModule;
import com.example.main.di.Scopes.AppScope;
import com.example.main.App;
import com.example.main.di.Module.AppActivityModule;
import com.example.main.di.Module.AppModule;
import com.example.main.di.Module.NetworkModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AppModule.class,
        AppActivityModule.class,
        AndroidSupportInjectionModule.class,
        NetworkModule.class,
        ServiceModule.class
    }
)
@AppScope
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(App app);
        AppComponent build();
    }

    @Override
    void inject(App instance);
}
