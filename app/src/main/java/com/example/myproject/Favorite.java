package com.example.myproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Favorite extends Fragment {
    private View v;
    private ArrayList<Restaurant> restaurants=new ArrayList<>();
    private MyAdapter1 adapter;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    private FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_favorite, container, false);
        // m muon do ve nhu nao ten thoi hay sao
        // ten vs anh
        recyclerView=v.findViewById(R.id.rv_list_restaurant);
        adapter=new MyAdapter1(restaurants);
        recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(),2));
        recyclerView.setAdapter(adapter);
//        doi ti t xem lai cach get
        auth=FirebaseAuth.getInstance();
        ref=FirebaseDatabase.getInstance().getReference().child("listFavorite").child(auth.getCurrentUser().getUid());
        Toast.makeText(v.getContext(), "id : "+auth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
//                    Restaurant res = ds.getValue(Restaurant.class);
//                    restaurants.add(res);
//                    Toast.makeText(v.getContext(), "name : "+res.getName(), Toast.LENGTH_SHORT).show();
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(v.getContext(), "error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        };
//        ref.addListenerForSingleValueEvent(valueEventListener);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Restaurant res=dataSnapshot.getValue(Restaurant.class);
//                    Log.d("AAA","name : "+res.getName());
                    restaurants.add(res);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }
}