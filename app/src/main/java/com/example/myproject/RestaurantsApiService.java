package com.example.myproject;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantsApiService {
    private static final String BASE_URL= "https://raw.githubusercontent.com/";
    private RestaurantsApi api;

    // tao api
    public RestaurantsApiService() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RestaurantsApi.class);
    }

    // tạo hàm nhận listRestaurants
    public Single<List<Restaurant>> getRestaurants() {
        return api.getRestaurants();
    }
}
