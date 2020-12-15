package com.example.myproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Detail_Restaurant extends Fragment {

    private Restaurant restaurant;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private Context context;
    private View viewItem;
    private ImageView imv_restaurant;
    private TextView Restaurant_name;
    private TextView Address;
    private TextView Rating;
    private TextView phoneNumber;
    private RecyclerView menu;
    TextView getComment;
    ImageView imv_favorite;
    private boolean checkComment = true;
    private boolean checkFavorite = true;

    public Detail_Restaurant(Restaurant restaurant) {

        this.restaurant = restaurant;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewComment = inflater.inflate(R.layout.fragment_detail__restaurant, container, false);

        ArrayList<String> menuRestaurant =restaurant.getMenu();
        MenuAdapter menuAdapter = new MenuAdapter(menuRestaurant);
        auth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference().child("listFavorite").child(auth.getCurrentUser().getUid());
        // :)) sao moi lan the phai dang nhap ak
        // lam o dau day
        imv_restaurant = viewComment.findViewById(R.id.imv_Restaurant);
        Restaurant_name = viewComment.findViewById(R.id.txt_name_Restaurant);
        Rating = viewComment.findViewById(R.id.txt_Rating);
        Address = viewComment.findViewById(R.id.txt_Address);
        phoneNumber = viewComment.findViewById(R.id.txt_phone);
        menu = viewComment.findViewById(R.id.rv_list_menu);
// hinh nhu do m chua dang nhap
        // de t dung dt t
        // may ao hay loi lam
        menu.setLayoutManager(new LinearLayoutManager(context));
        menu.setAdapter(menuAdapter);

        Glide.with(context)
                .load(restaurant.getPicture())
                .placeholder(R.drawable.anim_rotate)
                .into(imv_restaurant);

        Restaurant_name.setText(restaurant.getName());
        Rating.setText(restaurant.getRating());
        Address.setText(restaurant.getAddress());
        phoneNumber.setText(restaurant.getContact());

        getComment = viewComment.findViewById(R.id.txt_Comment_label);

        getComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadFragment(new Comment_2(restaurant, checkComment));
               checkComment = false;
            }
        });

        imv_favorite = viewComment.findViewById(R.id.img_favorite);
        imv_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFavorite = true;

                ref.push().setValue(restaurant);
                Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
            }
        });




        return viewComment;
    }

    void delete(String time){
        Query query = ref.orderByChild("time").equalTo(time);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    dataSnapshot1.getRef().removeValue();
                    Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
 
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}