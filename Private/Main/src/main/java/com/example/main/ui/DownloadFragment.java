package com.example.main.ui;


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
import android.widget.ProgressBar;


import com.example.main.R;
import com.example.main.adapter.MessageListAdapter;
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
    private ProgressBar progressBar;
    private MessageListAdapter messageListAdapter;
    private List<Message> messageList;
    private List<Message> excludedMessages = new ArrayList<>();


    @Inject
    MessageListViewModel viewModel;

    @Inject
    MainActivityController mainActivityController;

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
        progressBar = getView().findViewById(R.id.progressBar);
        progressBar.setVisibility(getView().VISIBLE);
        recyclerView = getView().findViewById(R.id.message_recycleView);
        mainActivityController.showNavBar();
    }


    private void initRecycler(){
        messageList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        messageListAdapter = new MessageListAdapter(messageList, this);
        recyclerView.setAdapter(messageListAdapter);
        if(recyclerView.getAdapter().getItemCount() > 0){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void updateMessageList() {
        viewModel.downloadMessages().observe(this, messages -> {
            messageList = messages;
            removeExcludedMessages();
            progressBar.setVisibility(getView().INVISIBLE);
            messageListAdapter.updateMessages(messageList);
        });

    }

    private void removeExcludedMessages(){
        for(Message message: messageList){
            for(Message excludedMessage: excludedMessages){
                if(message.getId().contains(excludedMessage.getId())){
                    messageList.remove(message);
                }
            }
        }
    }

    @Override
    public void onResume() {
        updateMessageList();
        super.onResume();
    }

    @Override
    public void showPopup(int position) {
        Message currentMessage = messageList.get(position);
        excludedMessages.clear();
        excludedMessages.add(currentMessage);
        DownloadFragmentDirections.DownloadToImage action = DownloadFragmentDirections.downloadToImage();
        action.setMessage(currentMessage);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
