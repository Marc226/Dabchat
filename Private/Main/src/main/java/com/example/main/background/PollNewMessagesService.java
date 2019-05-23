package com.example.main.background;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.example.main.R;
import com.example.main.di.Module.LoginModule;
import com.example.main.di.Module.UploadModule;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.MutableLiveData;
import dagger.Module;
import dagger.android.AndroidInjection;


public class PollNewMessagesService extends Service {
    private Executor executor;
    private static String CHANNEL_ID = "Message Notification Channel";
    private List<User> currentUsers;
    private boolean running = false;

    @Inject
    IMessageRepository messageRepository;

    public PollNewMessagesService() {
    }

    public void run() {
        running = true;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        executor = Executors.newSingleThreadExecutor();
        System.out.println("BackgroundService Started!");
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("BackgroundService Start Command!");
        createNotificationChannel();
        currentUsers = new ArrayList<>();
        final Service s = this;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(messageRepository!=null && running) {
                    MutableLiveData<List<User>> users = new MutableLiveData<>();
                    messageRepository.receiveFriendsWithPendingMessages(null, users);



                    if(messageRepository.getPendingFromUsers()!=null && userListChanged(messageRepository.getPendingFromUsers())) {
                        String fromUsers = "";
                        for (User user : messageRepository.getPendingFromUsers())
                            fromUsers += user.getMail() + "\n";

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setSmallIcon(R.drawable.dabandroid)
                                .setContentTitle("DabChat Message Received!")
                                .setContentText(fromUsers)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText(fromUsers));
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(s);

                        // notificationId is a unique int for each notification that you must define
                        notificationManager.notify(0, builder.build());
                    }
                }

                try {
                    Thread.sleep(5000);
                    run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executor.execute(r);
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("BackgroundService Destroyed!");
    }

    private boolean userListChanged(List<User> users) {
        if(this.currentUsers.containsAll(users)) return false;

        currentUsers.clear();
        currentUsers.addAll(users);

        return true;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.background_channel_name);
            String description = getString(R.string.background_channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}