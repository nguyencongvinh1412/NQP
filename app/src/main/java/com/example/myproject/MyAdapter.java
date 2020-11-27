package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

    public MyAdapter(List<Restaurant> restaurants) {
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewItem = itemView;
            context = viewItem.getContext();
            imv_restaurant = viewItem.findViewById(R.id.imv_Restaurant);
            Restaurant_name = viewItem.findViewById(R.id.txt_name_for_restaurant);
            Address = viewItem.findViewById(R.id.txt_address);
            Rating = viewItem.findViewById(R.id.txt_rating);
        }
    }
}
