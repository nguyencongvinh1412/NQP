package com.example.myproject.comment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.listmenu.MenuAdapter;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

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

    public  CommentAdapter (ArrayList<String> commentSet){
        this.commentSet = commentSet;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_comment, viewGroup, false);

        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder viewHolder, int position) {
        Log.i("123123", commentSet.get(position));
        viewHolder.getTextCommentView().setText(commentSet.get(position));
    }

    @Override
    public int getItemCount() {
        return commentSet.size();
    }
}
