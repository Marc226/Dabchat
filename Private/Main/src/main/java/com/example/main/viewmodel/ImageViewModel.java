package com.example.main.viewmodel;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.main.R;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.model.Message;
import com.example.main.utils.ApplicationConfig;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

public class ImageViewModel extends ViewModel {

    public ApplicationConfig config;
    public IMessageRepository messageRepository;

    public ImageViewModel(ApplicationConfig config, IMessageRepository messageRepository) {
        this.config = config;
        this.messageRepository = messageRepository;
    }

    public void loadImage(Fragment fragment, String id, ImageView view){
        StringBuilder sb = new StringBuilder();
        sb.append(config.WEBSERVICE_URL);
        sb.append("send/getImage/");
        sb.append(id);
        Log.d("test", sb.toString());
        String url = sb.toString();
        Glide
            .with(fragment)
            .load(url)
            .placeholder(R.drawable.ic_downloading_96dp)
            .error(R.drawable.ic_image_error_48dp)
            .dontAnimate()
            .fitCenter()
            .into(view);
    }

    public void removeRecipent(Message currentMessage){
        messageRepository.removeUserFromRecipients(currentMessage);
    }
}
