package com.example.main.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.main.R;
import com.example.main.model.Message;
import com.example.main.ui.DownloadFragment;

import java.util.ArrayList;
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
            messageName = itemView.findViewById(R.id.message_recycleView);

            this.noteListner = onMessageNoteListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            noteListner.showPopup();
        }
    }




    public interface OnMessageNoteListner{
        void showPopup();
    }

    public void updateMessages(List<Message> messages){
        this.messageList = messages;
        notifyDataSetChanged();
    }

    public void showPopup() {
        View popupView = LayoutInflater.from(this.parent.getContext()).inflate(R.layout.message_popup, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        Button btnDismiss = popupView.findViewById(R.id.close_button);
        ImageView messageView = popupView.findViewById(R.id.popup_imageView);
        byte[] imageArray = currentMessage.getImage();


        Bitmap bmp = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
        messageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, messageView.getWidth(), messageView.getHeight(), false));



        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(popupView, 0, 0);
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
        holder.messageName.setText(currentMessage.getFromUser().getFirstName());
    }


}
