package com.soft.weather.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by minos on 10.01.2017.
 */

public class ApiHelper {
    private static Retrofit mRestRetrofit;
    private static ApiInterface apiInterface;

    static {
        mRestRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = mRestRetrofit.create(ApiInterface.class);
    }

    public static Call<WeatherMap> getWeatherMap() {
        return apiInterface.getWeatherMap();

    }
}
