package com.example.main.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.main.R;
import com.example.main.model.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import dagger.android.support.DaggerFragment;

public class PopupFragment extends DaggerFragment {

    private ImageView message_imageView;
    private Bitmap bmp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        message_imageView = view.findViewById(R.id.popUpImageView);
        Message message = PopupFragmentArgs.fromBundle(getArguments()).getMessage();
        byte[] imageArray = message.getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);

        ViewTreeObserver vto = message_imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                message_imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                message_imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(), false));
                return true;
            }
        });

        message_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_popup, container, false);
    }


}
