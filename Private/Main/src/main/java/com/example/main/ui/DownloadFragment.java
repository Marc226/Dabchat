package com.example.main.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.main.R;
import com.example.main.adapter.MessageListAdapter;
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


    public DownloadFragment() {
        // Required empty public constructor
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
    public void showPopup() {
        messageListAdapter.showPopup();
    }
}