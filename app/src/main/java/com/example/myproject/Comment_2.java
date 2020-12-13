package com.example.myproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Comment_2 extends Fragment implements ValueEventListener {

    private Restaurant restaurant;
    private RecyclerView recyclerCommentView;
    private Context context;
    private ImageView imv_restaurant;
    private TextView txt_restaurant_name;
    private EditText edit_comment;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<String> listComment = new ArrayList<String>();
    private Button btnComment;
    private  ArrayList<String> comment;
    private CommentAdapter commentAdapter;
    private boolean checkMerge;



    int positionComment;

    public Comment_2(Restaurant restaurant, boolean checkM) {
        this.restaurant = restaurant;
        this.checkMerge = checkM;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewComment = inflater.inflate(R.layout.fragment_comment_2, container, false);
        recyclerCommentView = viewComment.findViewById(R.id.rv_list_comment);
        imv_restaurant = viewComment.findViewById(R.id.imv_Restaurant);
        txt_restaurant_name = viewComment.findViewById(R.id.txt_name_Restaurant);
        edit_comment = viewComment.findViewById(R.id.edt_comment);
        btnComment = viewComment.findViewById(R.id.btnComment);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("listComment").child(restaurant.getName());
        databaseReference.addValueEventListener(this);
        // Inflate the layout for this fragment
        comment = restaurant.getComment();

        commentAdapter = new CommentAdapter(comment);
        recyclerCommentView.setAdapter(commentAdapter);

        // xử lý sự kiện lưu comment vào firebase dưới dạng list
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_comment.getText().toString().trim() != "")
                {
                    listComment.add(edit_comment.getText().toString().trim());
                    comment.add(edit_comment.getText().toString().trim());
                    commentAdapter.notifyDataSetChanged();
                    databaseReference.setValue(listComment);
                }
            }
        });

        txt_restaurant_name.setText(restaurant.getName());

        Glide.with(context)
                .load(restaurant.getPicture())
                .placeholder(R.drawable.anim_rotate)
                .into(imv_restaurant);

        recyclerCommentView.setLayoutManager(new LinearLayoutManager(context));
        return viewComment;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.getValue() != null)
        {
            if(checkMerge == true)
            {
                listComment = (List<String>) snapshot.getValue();
                mergeListComment();
            }
            else
            {
                listComment = (List<String>) snapshot.getValue();
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    public void mergeListComment()
    {
        checkMerge = false;
        for(int i = 0;i<listComment.size();i++)
        {
            comment.add(listComment.get(i));
        }
        commentAdapter.notifyDataSetChanged();
    }
}