package com.example.myproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<com.example.myproject.CommentAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textCommentView;

        public ViewHolder(View view) {
            super(view);

            textCommentView = (TextView) view.findViewById(R.id.comment_text);
        }

        public TextView getTextCommentView(){
            return textCommentView;
        }
    }
    private ArrayList<String> commentSet;

    public CommentAdapter(ArrayList<String> commentSet){
        this.commentSet = commentSet;
    }

    @NonNull
    @Override
    public com.example.myproject.CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_show_comment, viewGroup, false);

        return new com.example.myproject.CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.myproject.CommentAdapter.ViewHolder viewHolder, int position) {
        //Log.i("123123", commentSet.get(position));
        viewHolder.getTextCommentView().setText(commentSet.get(position));
    }

    @Override
    public int getItemCount() {
        return commentSet.size();
    }
}
