package com.example.myproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM Restaurant")
    public List<Restaurant> getAllRestaurant();
    @Insert
    public void insertRestaurant(Restaurant...Restaurant);
    @Delete
    public void deleteRestaurant(Restaurant Restaurant);
}
