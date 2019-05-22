package com.example.main.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import dagger.android.support.DaggerFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.interfaces.MainActivityController;
import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.presenter.FriendListViewModel;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;



public class UploadFragment extends DaggerFragment  {

    @Inject
    MainActivityController mainActivityController;
    @Inject
    FriendListViewModel viewModel;



    private File imageFile;
    private ImageView upload_imageView;
    private Button upload_button;
    private Button send_button;
    private Button add_friend_button;
    EditText textfield_email;
    byte[] imageData;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    public UploadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initUI();
        mainActivityController.showNavBar();
        upload_button.setOnClickListener(v -> openGallery());
        send_button.setOnClickListener(v -> viewModel.sendMessage(new Message(null, imageData)).observe(getViewLifecycleOwner(), s -> displayToast(s)));
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
        upload_imageView = getView().findViewById(R.id.preview);
        upload_button = getView().findViewById(R.id.upload_btn);
        send_button = getView().findViewById(R.id.send_button);
        add_friend_button = getView().findViewById(R.id.button_add_friend);
        textfield_email = getView().findViewById(R.id.textfield_email);

        add_friend_button.setOnClickListener(view ->
                System.out.println(textfield_email.getText())
        );
    }

    private void displayToast(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }





}
