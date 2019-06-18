package com.example.main;



import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.main.di.Component.AppComponent;
import com.example.main.di.Component.DaggerAppComponent;



import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class App extends DaggerApplication {


    public static String CHANNEL_ID = "Message Notification Channel1";
    private AppComponent app;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        app = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        return app;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.background_channel_name);
            String description = getString(R.string.background_channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
