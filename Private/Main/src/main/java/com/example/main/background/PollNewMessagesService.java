package com.example.main.background;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.main.R;
import com.example.main.interfaces.ILoginRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.User;
import com.example.main.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.MutableLiveData;
import dagger.android.AndroidInjection;

import static com.example.main.App.CHANNEL_ID;


public class PollNewMessagesService extends Service {
    public static final String TAG = "Notification Service";
    private List<User> usersThatHasSent;

    @Inject
    IMessageRepository messageRepository;
    @Inject
    ILoginRepository loginRepository;
    @Inject
    ExecutorService executor;

    public PollNewMessagesService() {
    }

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        System.out.println("BackgroundService Started!");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("BackgroundService Start Command!");
        usersThatHasSent = new ArrayList<>();
        final Service s = this;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if(messageRepository!=null) {
                    MutableLiveData<List<User>> users = new MutableLiveData<>();
                    messageRepository.receiveFriendsWithPendingMessages(null, users);
                    if(messageRepository.getPendingFromUsers()!=null && messageRepository.getPendingFromUsers().size()>0) {
                        Log.d(TAG, "run: notfication triggered");
                        String fromUsers = "";
                        for (User user : messageRepository.getPendingFromUsers())
                            fromUsers += user.getMail() + "\n";

                        Intent resultIntent = new Intent(s, MainActivity.class);
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(s);
                        stackBuilder.addNextIntentWithParentStack(resultIntent);
                        PendingIntent resultPendingIntent =
                                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_notify_24dp)
                                .setContentTitle("DabChat Message Received!")
                                .setContentText(fromUsers)
                                .setContentIntent(resultPendingIntent)
                                .setAutoCancel(true)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText(fromUsers));
                        NotificationManager notificationManager = getSystemService(NotificationManager.class);

                        // notificationId is a unique int for each notification that you must define
                        notificationManager.notify(0, builder.build());
                        messageRepository.getPendingFromUsers().clear();
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
        executor.submit(r);
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("BackgroundService Destroyed!");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}