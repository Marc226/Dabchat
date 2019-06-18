package com.example.main.background;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.ServiceImpl.MessageRepository;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.Message;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import dagger.android.AndroidInjection;

public class UploadService extends JobIntentService {
    public static final String TAG = "UploadService";
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private File imageFile;
    private byte[] imageData;

    @Inject
    IMessageRepository messageRepository;

    public UploadService() {
    }

    public static void enqueueWork(Context context, Intent intent){
        enqueueWork(context, UploadService.class, 2, intent);
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
        Log.d(TAG, "onCreate: UploadService created");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork: Upload Started!");
        Uri imageUri = Uri.parse(intent.getStringExtra(getString(R.string.uriUploadID)));
        imageFile = new File(imageUri.getPath());

        try (InputStream imageS = this.getContentResolver().openInputStream(imageUri)) {
            Bitmap bm = BitmapFactory.decodeStream(imageS);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            imageData= baos.toByteArray();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        Message message = intent.getParcelableExtra(getString(R.string.messageUploadId));
        message.setByteImage(imageData);
        messageRepository.sendMessage(message);
        showToast(getString(R.string.uploadStarted));
    }

    @Override
    public void onDestroy() {
        showToast(getString(R.string.uploadFinish));
        super.onDestroy();
    }


    @Override
    public boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
    }



    void showToast(final String message){
        mHandler.post(() -> Toast.makeText(UploadService.this, message, Toast.LENGTH_SHORT).show());
    }
}
