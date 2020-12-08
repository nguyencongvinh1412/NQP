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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Detail_Restaurant extends Fragment {

    private Restaurant restaurant;

    private Context context;
    private View viewItem;
    private ImageView imv_restaurant;
    private TextView Restaurant_name;
    private TextView Address;
    private TextView Rating;
    private TextView phoneNumber;
    private RecyclerView menu;

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

        imv_restaurant = viewComment.findViewById(R.id.imv_Restaurant);
        Restaurant_name = viewComment.findViewById(R.id.txt_name_Restaurant);
        Rating = viewComment.findViewById(R.id.txt_Rating);
        Address = viewComment.findViewById(R.id.txt_Address);
        phoneNumber = viewComment.findViewById(R.id.txt_phone);
        menu = viewComment.findViewById(R.id.rv_list_menu);

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

        TextView getComment = viewComment.findViewById(R.id.txt_Comment_label);
        getComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loadFragment(new Comment_2(restaurant));
            }
        });
        return viewComment;
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