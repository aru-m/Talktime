package com.example.talktime.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talktime.Models.Message;
import com.example.talktime.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    private ArrayList<Message> messages;
    private Context context;
    private final int SENDER_VIEW_TYPE = 1,RECEIVER_VIEW_TYPE = 2;
    private String receiverId;

    public ChatAdapter(ArrayList<Message> messages, Context context){
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        try{
            if(messages.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
                return SENDER_VIEW_TYPE;
            }else{
                return RECEIVER_VIEW_TYPE;
            }
        }catch (Exception e){
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }

        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder) holder).senderMsg.setText(message.getMessage());
        }
        else{
            ((ReceiverViewHolder)holder).receiverMsg.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
    // there will be 2 viewHolder because there is sender and receiver message

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        private TextView receiverMsg;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.receiverText);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{
        private TextView senderMsg;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
        }
    }

}
