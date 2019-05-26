package com.example.main.ui;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;


import com.example.main.R;
import com.example.main.adapter.MessageListAdapter;
import com.example.main.interfaces.IMessageRepository;
import com.example.main.interfaces.MainActivityController;
import com.example.main.model.Message;
import com.example.main.presenter.MessageListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends DaggerFragment implements MessageListAdapter.OnMessageNoteListner {

    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private Button donwload_button;
    private MessageListAdapter messageListAdapter;
    private List<Message> messageList;


    @Inject
    MessageListViewModel viewModel;

    @Inject
    MainActivityController mainActivityController;
    @Inject
    IMessageRepository messageRepository;

    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUI();
        initRecycler();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download, container, false);
    }

    private void initUI() {
        recyclerView = getView().findViewById(R.id.message_recycleView);
        this.donwload_button = getView().findViewById(R.id.download_button);
        /*this.donwload_button.setOnClickListener(v -> {
            updateMessageList();
        });*/
    }


    private void initRecycler(){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        messageListAdapter = new MessageListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(messageListAdapter);
        updateMessageList();
    }

    private void updateMessageList() {
        viewModel.downloadMessages().observe(this, messages -> {
            messageList = messages;
            messageListAdapter.updateMessages(messageList);
        });

    }

    @Override
    public void showPopup(int position) {
        Message currentMessage = messageList.get(position);

        byte[] imageArray = currentMessage.getImage();

        DownloadFragmentDirections.DownloadToImage action = DownloadFragmentDirections.downloadToImage();
        action.setMessage(currentMessage);

        messageRepository.removeUserFromRecipients(currentMessage);
        Navigation.findNavController(getView()).navigate(action);



        //popupWindow.showAsDropDown(popupView, 0, 0);
    }


}
