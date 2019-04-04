package com.example.uploadfeature.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.uploadfeature.R;

import static android.app.Activity.RESULT_OK;


public class Upload_fragment extends Fragment {
    ImageView upload_imageView;
    Button upload_button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    public Upload_fragment() {
        // Required empty public constructor
    }

    public static Upload_fragment newInstance(String param1, String param2) {
        Upload_fragment fragment = new Upload_fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        upload_imageView = (ImageView) getView().findViewById(R.id.imageView);
        upload_button = (Button) getView().findViewById(R.id.upload_btn);

        //initUI();
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upload_fragment, container, false);
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
    }


}
