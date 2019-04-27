package com.example.main.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.Interceptor;
import okhttp3.Request;

public class NetworkUtil {
    private final String TAG = "NETWORK-UTIL";
    Context context;

    @Inject
    public NetworkUtil(@Named("App-Context") Context context) {
        this.context = context;
    }


    public boolean hasNetwork() {
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connection.getActiveNetworkInfo();
        Log.d(TAG, "Network connection established");
        if(network !=null && network.isConnected()){
            return true;
        }
        return false;
    }

    public Interceptor responseInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            if (checkControl(cacheControl)) {
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 5000)
                        .build();
            } else {
                return originalResponse;
            }
        }
    };

    private boolean checkControl(String cacheControl){
        if(cacheControl == null ||
            cacheControl.contains("no-store") ||
            cacheControl.contains("no-cache") ||
            cacheControl.contains("must-revalidate") ||
            cacheControl.contains("max-age=0")){
            return true;
        }
        return false;
    }

    public Interceptor responseInterceptorOffline = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!hasNetwork()) {
                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-age=" + 67800)
                        .build();
            }
            return chain.proceed(request);
        }
    };

}