package com.example.main.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.main.R;
import com.example.main.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder> {

    private List<User> friendList;
    private OnFriendNoteListner onNoteListener;

    @NonNull
    @Override
    public FriendListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class FriendListViewHolder extends RecyclerView.ViewHolder{

        private TextView friendName;
        private OnFriendNoteListner noteListner;

        public FriendListViewHolder(@NonNull View itemView, OnFriendNoteListner onFriendNoteListner) {
            super(itemView);
            friendName = itemView.findViewById(R.id.friendNameRecycler);

            this.noteListner = onFriendNoteListner;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public interface OnFriendNoteListner{
        boolean isClicked
        void add(int position);
        void remove(int position);
    }
}
