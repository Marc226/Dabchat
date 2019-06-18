package com.example.main.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.main.R;
import com.example.main.model.Message;
import com.example.main.viewmodel.ImageViewModel;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import dagger.android.support.DaggerFragment;

public class PopupFragment extends DaggerFragment {

    @Inject
    ImageViewModel viewModel;

    @Inject
    ExecutorService executorService;

    private ImageView message_imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        message_imageView = view.findViewById(R.id.popUpImageView);
        Message message = PopupFragmentArgs.fromBundle(getArguments()).getMessage();
        viewModel.loadImage(this, message.getStringImage(), message_imageView);
        executorService.submit(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            viewModel.removeRecipent(message);
        });
        message_imageView.setOnClickListener(v -> {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack();
        });
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_popup, container, false);
    }


}
