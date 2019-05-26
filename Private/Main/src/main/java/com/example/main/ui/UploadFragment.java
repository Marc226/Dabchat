package com.example.main.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.main.R;
import com.example.main.adapter.FriendListAdapter;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.interfaces.MainActivityController;
import com.example.main.model.Message;
import com.example.main.model.User;
import com.example.main.presenter.FriendListViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

import static android.app.Activity.RESULT_OK;



public class UploadFragment extends DaggerFragment implements FriendListAdapter.OnFriendNoteListner {

    @Inject
    MainActivityController mainActivityController;
    @Inject
    FriendListViewModel viewModel;
    @Inject
    IMessageRepository userRep;

    private RecyclerView recyclerView;
    private File imageFile;
    private ImageView upload_imageView;
    private Button upload_button;
    private Button send_button;
    private Button add_friend_button;
    private Button logoutBut;

    private RecyclerView.LayoutManager layoutManager;
    private FriendListAdapter friendListAdapter;
    private EditText textfield_email;
    byte[] imageData;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    private List<User> targetFriends;
    private List<User> friendList;
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
        initRecycler();
        mainActivityController.showNavBar();
    }

    private void initRecycler(){
        targetFriends = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        friendListAdapter = new FriendListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(friendListAdapter);
        updateFriendList();
    }

    private void updateFriendList(){
        viewModel.getFriendList().observe(this, users -> {
                friendList = users;
                friendListAdapter.updateFriends(users);
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
            upload_imageView.setVisibility(View.VISIBLE);
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
        recyclerView = getView().findViewById(R.id.friendRecycler);
        upload_imageView = getView().findViewById(R.id.preview);
        upload_button = getView().findViewById(R.id.upload_btn);
        send_button = getView().findViewById(R.id.send_button);
        add_friend_button = getView().findViewById(R.id.button_add_friend);
        textfield_email = getView().findViewById(R.id.textfield_email);
        logoutBut = getView().findViewById(R.id.logoutBut);

        add_friend_button.setOnClickListener(view ->
                {
                    viewModel.addFriend(textfield_email.getText().toString()).observe(this, s -> {
                        updateFriendList();
                        displayToast(s);
                    });
                }
        );
        logoutBut.setOnClickListener(v -> {
            userRep.clearPending();
            viewModel.Logout();
            mainActivityController.logout();
        });
        upload_button.setOnClickListener(v -> openGallery());
        send_button.setOnClickListener(v -> {
            if (!targetFriends.isEmpty() && imageData != null) {
                Message m = new Message(null, imageData);
                for(User friend : targetFriends) {
                    m.addRecipient(friend.getId());
                }
                viewModel.sendMessage(m).observe(getViewLifecycleOwner(), s -> displayToast(s));
            } else if(targetFriends.isEmpty()){
                displayToast("Please select a friend");
            } else {
                displayToast("Please select a picture");
            }
        });
    }

    private void displayToast(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean isClicked(int position) {
        return targetFriends.contains(friendList.get(position));
    }

    @Override
    public void add(int position) {
        targetFriends.add(friendList.get(position));
    }

    @Override
    public void remove(int position) {
        targetFriends.remove(friendList.get(position));
    }
}
