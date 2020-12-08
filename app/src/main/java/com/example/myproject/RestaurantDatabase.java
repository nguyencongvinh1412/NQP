package com.example.myproject;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Restaurant.class, version = 1)
@TypeConverters({Converters.class})
public abstract class RestaurantDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
    public static RestaurantDatabase instance;

    public static RestaurantDatabase getInstance(Context mcontext) {
        if(instance == null)
        {
            instance = Room.databaseBuilder(mcontext,RestaurantDatabase.class, "database-dog").build();
        }
        return instance;
    }

}
