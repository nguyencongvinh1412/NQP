package com.example.myproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Restaurant> restaurants;
    private Context context;
    private ItemClickListener listener;
    // dặt 3 biến hằng
    // nếu trạng thái ở biến 1 thì cho hiển thị danh sách nhà hàng
    // nếu trang thái ở biến 2 thì cho hiển thị danh sách menu trong nhà hàng
    // nếu trạng thái ở biến 3 thì cho hiên thị danh sách comment
    private final int showMenuRestaurant = 1;
    private final int showMenuForFood = 2;
    private final  int showMenuComment = 3;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    ImageView imv_favorite;

    public MyAdapter1(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // nếu trạng thái là 1 : hiển thị danh sách các nhà hàng
        // lưa chọn hiển thị trong layout : itemview.xml
//        if(viewType == 1)
//        {
//            View viewRestaurant = (View)inflater.inflate(R.layout.itemview, parent, false);
//            MyViewHolder myViewHolder = new MyViewHolder(viewRestaurant);
//            return  myViewHolder;
//        }
        View viewRestaurant = (View)inflater.inflate(R.layout.itemview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(viewRestaurant);
        return  myViewHolder;
    }

    // lấy trạng thái hiển thị
    @Override
    public int getItemViewType(int position) {
        if(restaurants.get(position).isShowMenu() == 1) return showMenuRestaurant;
        else if(restaurants.get(position).isShowMenu() == 2) return showMenuForFood;
        return showMenuComment;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // kiểm tra đối tượng được tạo là đối tượng nào
        // nếu đối tượng alf MyViewHolder --> hiển thị danh sách nhà hàng
        if(holder instanceof  MyViewHolder)
        {
            Glide.with(context)
                    .load(restaurants.get(position).getPicture())
                    .placeholder(R.drawable.anim_rotate)
                    .into(((MyViewHolder)holder).imv_restaurant);

            (((MyViewHolder)holder).restaurant_card).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadFragment(new Detail_Restaurant(restaurants.get(position)));
                }
            });
            ((MyViewHolder)holder).Restaurant_name.setText(restaurants.get(position).getName());
            ((MyViewHolder)holder).Address.setText(restaurants.get(position).getAddress());
            ((MyViewHolder)holder).Rating.setText(restaurants.get(position).getRating());
        }
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setOnItemClick(ItemClickListener listener){
        this.listener = listener;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        private View viewItem;
        private ImageView imv_restaurant;
        private TextView Restaurant_name;
        private TextView Address;
        private TextView Rating;
        private CardView restaurant_card;
//dc thi hien thi het cx dc, tuy m cak
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewItem = itemView;
            context = viewItem.getContext();
            imv_restaurant = viewItem.findViewById(R.id.imv_Restaurant);
            Restaurant_name = viewItem.findViewById(R.id.txt_name_for_restaurant);
            Address = viewItem.findViewById(R.id.txt_address);
            Rating = viewItem.findViewById(R.id.txt_rating);
            restaurant_card = viewItem.findViewById(R.id.restaurant_card);

//            public void favoriteChecker(final String postkey){
//                imv_favorite = viewItem.findViewById(R.id.img_favorite);
//                ref = FirebaseDatabase.getInstance().getReference().child("listFavorite").child(auth.getCurrentUser().getUid());
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                final String uid = user.getUid();
//
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        if (snapshot.child(postkey).hasChild(uid)){
//                            imv_favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
//                        }else {
//                            imv_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//            }
        }
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
