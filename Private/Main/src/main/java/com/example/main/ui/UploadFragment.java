package com.example.main.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.example.main.R;
import com.example.main.interfaces.MainActivityController;
import com.example.main.interfaces.UploadContract;
import com.example.main.model.Message;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;



public class UploadFragment extends DaggerFragment implements UploadContract.iUploadView {

    @Inject
    MainActivityController mainActivityController;
    @Inject
    UploadContract.iUploadPresenter presenter;


    File imageFile;
    ImageView upload_imageView;
    Button upload_button;
    Button send_button;
    byte[] imageData;
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
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message(null, imageData);

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

            try (InputStream imageS = this.getContext().getContentResolver().openInputStream(imageUri)) {
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
        upload_imageView = (ImageView) getView().findViewById(R.id.preview);
        upload_button = (Button) getView().findViewById(R.id.upload_btn);
        send_button = (Button) getView().findViewById(R.id.send_button);
    }


    @Override
    public void displayToast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
