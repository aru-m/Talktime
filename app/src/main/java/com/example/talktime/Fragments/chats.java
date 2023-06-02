package com.example.talktime.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.talktime.Adapters.RecyclerViewAdapter;
import com.example.talktime.Models.User;
import com.example.talktime.databinding.FragmentChatsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chats extends Fragment {

    public chats() {
    }

    private FragmentChatsBinding binding;
    private ArrayList<User> list = new ArrayList<>();
    private FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentChatsBinding.inflate(inflater, container, false);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list,getContext());
        binding.chatRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance();
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    user.setUserId(dataSnapshot.getKey());
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return binding.getRoot();
    }
}