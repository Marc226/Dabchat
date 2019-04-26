package com.example.main.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.main.R;
import dagger.android.support.DaggerFragment;

public class FriendListFragment extends DaggerFragment {
    public FriendListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.friendlist_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
