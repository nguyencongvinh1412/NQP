package com.example.myproject.comment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;
import com.example.myproject.database.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Comment extends Fragment {

    private List<Restaurant> restaurantList;
    private RecyclerView recyclerCommentView;
    private Context context;

    int positionComment;
    public Comment (int position, List<Restaurant> restaurantList){
        this.positionComment = position;
        this.restaurantList = restaurantList;
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
        View viewComment = inflater.inflate(R.layout.fragment_comment, container, false);

        // Inflate the layout for this fragment
        ArrayList<String> comment = restaurantList.get(positionComment).getComment();
        CommentAdapter commentAdapter = new CommentAdapter(comment);
        recyclerCommentView = viewComment.findViewById(R.id.rv_list_comment);
        recyclerCommentView.setLayoutManager(new LinearLayoutManager(context));
        recyclerCommentView.setAdapter(commentAdapter);
        return viewComment;
    }
}