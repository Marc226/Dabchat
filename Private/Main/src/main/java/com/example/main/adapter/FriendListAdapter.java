package com.example.main.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
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

    public FriendListAdapter(List<User> friendList, OnFriendNoteListner onNoteListener) {
        this.friendList = friendList;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public FriendListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item, parent, false);
        FriendListViewHolder fvh = new FriendListViewHolder(v, onNoteListener);
        return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendListViewHolder holder, int position) {
        User user = friendList.get(position);
        holder.itemView.setBackgroundColor(Color.WHITE);
        holder.friendName.setText(user.getMail());
    }

    public void updateFriends(List<User> friends){
        this.friendList = friends;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public static class FriendListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView friendName;
        private OnFriendNoteListner noteListner;

        public FriendListViewHolder(@NonNull View itemView, OnFriendNoteListner onFriendNoteListner) {
            super(itemView);
            friendName = itemView.findViewById(R.id.friendNameRecycler);

            this.noteListner = onFriendNoteListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(noteListner.isClicked(getAdapterPosition())){
                itemView.setBackgroundColor(Color.WHITE);
                noteListner.remove(getAdapterPosition());
            } else {
                itemView.setBackgroundColor(Color.rgb(0,133,119));
                noteListner.add(getAdapterPosition());
            }
        }
    }

    public interface OnFriendNoteListner{
        boolean isClicked(int position);
        void add(int position);
        void remove(int position);
    }
}
