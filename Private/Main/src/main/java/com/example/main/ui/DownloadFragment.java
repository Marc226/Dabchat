package com.example.main.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.main.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends DaggerFragment {

    private RecyclerView recyclerView;
    private Button donwload_button;

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


}
