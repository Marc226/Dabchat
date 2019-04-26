package com.example.main.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.android.support.DaggerFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.main.R;
import com.example.main.interfaces.MainActivityController;
import com.example.main.interfaces.UploadContract;
import com.example.main.model.Message;

import java.io.File;

import javax.inject.Inject;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;



public class UploadFragment extends DaggerFragment {

    @Inject
    MainActivityController mainActivityController;
    @Inject
    UploadContract.iUploadPresenter presenter;
    File imageFile;
    ImageView upload_imageView;
    Button upload_button;
    Button send_button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    public UploadFragment() {
        // Required empty public constructor
    }

    public static UploadFragment newInstance() { return new UploadFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initUI();
        mainActivityController.showNavBar();
        //initUI();
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendMessage(new Message(null, imageFile));
            }
        });

    }

        private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK);
        gallery.setType("image/*");
        startActivityForResult(gallery, PICK_IMAGE);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE ){
            imageUri = data.getData();
            upload_imageView.setImageURI(imageUri);
            imageFile = new File(imageUri.getPath());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.upload_fragment, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initUI(){
        upload_imageView = (ImageView) getView().findViewById(R.id.imageView);
        upload_button = (Button) getView().findViewById(R.id.upload_btn);
        send_button = (Button) getView().findViewById(R.id.send_button);
    }


}
