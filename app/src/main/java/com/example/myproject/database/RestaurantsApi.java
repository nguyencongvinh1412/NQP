package com.example.myproject.database;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface RestaurantsApi {
    @GET("nguyencongvinh1412/dogsApi/master/resaurantApi.json")
    Single<List<Restaurant>> getRestaurants();
}
