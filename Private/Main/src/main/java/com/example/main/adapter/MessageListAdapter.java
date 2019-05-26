package com.example.main.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.main.R;
import com.example.main.model.Message;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>{
    private List<Message> messageList;
    private OnMessageNoteListner messageNoteListener;
    private Message currentMessage;
    private ViewGroup parent;

    public MessageListAdapter(List<Message> messageList, OnMessageNoteListner messageNoteListener) {
        this.messageList = messageList;
        this.messageNoteListener = messageNoteListener;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView messageName;
        private OnMessageNoteListner noteListner;

        public MessageListViewHolder(@NonNull View itemView, OnMessageNoteListner onMessageNoteListner) {
            super(itemView);
            messageName = itemView.findViewById(R.id.messageNameRecycler);

            this.noteListner = onMessageNoteListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            System.out.println("Hello");
            noteListner.showPopup(getAdapterPosition());
        }
    }




    public interface OnMessageNoteListner{
        void showPopup(int position);
    }

    public void updateMessages(List<Message> messages){
        this.messageList = messages;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MessageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        this.parent = parent;
        MessageListViewHolder mvh = new MessageListViewHolder(v, this.messageNoteListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListViewHolder holder, int position) {
       this.currentMessage = messageList.get(position);
        System.out.println("messageName: "+holder.messageName);


        holder.messageName.setText(currentMessage.getFromUser().getMail());
    }


}
