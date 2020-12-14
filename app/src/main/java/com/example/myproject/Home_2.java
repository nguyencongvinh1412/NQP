package com.example.myproject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Home_2 extends Fragment {

    private ArrayList<Restaurant> restaurants;
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private Context context;
    private View view;
    private SearchView searchView;

    RestaurantsApiService restaurantApiService;
    RestaurantDatabase restaurantDatabase;
    RestaurantDao restaurantDao;

    public Home_2(){

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_2, container, false);
        setHasOptionsMenu(true);
        recyclerView =(RecyclerView)view.findViewById(R.id.rv_list_restaurant);
        context = view.getContext();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        restaurants = new ArrayList<>();
        adapter = new MyAdapter(restaurants);
        recyclerView.setAdapter(adapter);
        restaurantApiService = new RestaurantsApiService();
        searchView = (SearchView) view.findViewById(R.id.search_restaurant);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Restaurant> tempArr = new ArrayList<>();
                for(int i = 0; i< restaurants.size();i++)
                {
                    if(restaurants.get(i).getName().startsWith(newText))
                    {
                        tempArr.add(restaurants.get(i));
                    }
                }
                MyAdapter tempAdapter = new MyAdapter(tempArr);
                recyclerView.setAdapter(tempAdapter);
                return false;
            }
        });
        loadFromRoom();

        return  view;
    }

    public void update() {
        List<Restaurant> curDog = new ArrayList<>();
        curDog.addAll(restaurants);
        for(Restaurant x:curDog) {
            restaurants.remove(0);
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    restaurantDatabase.restaurantDao().deleteRestaurant(x);
                }
            });
        }
        restaurantApiService.getRestaurants()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Restaurant>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Restaurant>  value) {
                        for(Restaurant x: value) {
                            restaurants.add(x);
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    restaurantDatabase.restaurantDao().insertRestaurant(x);
                                }
                            });
                        }
                        adapter = new MyAdapter(restaurants);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // load data từ database
    private void loadFromRoom() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                restaurantDatabase = RestaurantDatabase.getInstance(context);
                restaurantDao = restaurantDatabase.restaurantDao();
                ArrayList<Restaurant> tempListDogs = (ArrayList<Restaurant>) restaurantDao.getAllRestaurant();
                if(tempListDogs.size() == 0) {
                    update();
                }
                else
                {
                    restaurants.addAll(tempListDogs);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}