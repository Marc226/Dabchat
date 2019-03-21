package com.example.main.di.Module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.common.Common.di.Scopes.AppScope;
import com.example.common.Common.utils.ApplicationConfig;
import com.example.common.Common.utils.NetworkUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @AppScope
    public OkHttpClient provideHttpClient(@Named("App-Context")Context context, NetworkUtil networkUtil){
        long cacheSize = (long) (5 * 1024 * 1024);
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkUtil.responseInterceptor)
                .addInterceptor(networkUtil.responseInterceptorOffline)
                .build();

    }


    @Provides
    @AppScope
    public Retrofit provideRetroFit(ApplicationConfig config, GsonConverterFactory gsonConverterFactory, OkHttpClient httpClient){
        return new Retrofit.Builder()
                .baseUrl(config.WEBSERVICE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(httpClient)
                .build();
    }

    @Provides
    @AppScope
    public GsonConverterFactory provideGsonFactory(){
        return GsonConverterFactory.create();
    }



}
