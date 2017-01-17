package com.soft.weather.api;

import com.soft.weather.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by minos on 10.01.2017.
 */

public interface ApiInterface {

    //довгота
    String lon = "24.71";
    //широта
    String lat = "48.92";


    @GET("weather?id=5128581&units=metric&appid=97949f3f4997070acc98165dca6ec08c")
    //@GET("weather?lat=41.39&lon=2.16&units=metric&appid=97949f3f4997070acc98165dca6ec08c")
    //@GET("weather?lat="+lat+"&lon="+lon+"&units=metric&appid=97949f3f4997070acc98165dca6ec08c")
    Call <WeatherMap> getWeatherMap();
}
