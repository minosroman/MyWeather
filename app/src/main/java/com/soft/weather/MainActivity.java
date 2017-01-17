package com.soft.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.CircleProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.soft.weather.api.ApiHelper;
import com.soft.weather.api.WeatherMap;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView city_name, main_temp, wind_speed, wind_direction, sunrise_time, sunset_time, min_temp, max_temp, description;
    DonutProgress donut_progress;
    CircleProgress circleProMin, circleProMax;
    ImageView wind_dir;
    ImageView condition_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city_name = (TextView) findViewById(R.id.city_name);
        description = (TextView) findViewById(R.id.tv_description);
        main_temp = (TextView) findViewById(R.id.main_temp);
        wind_speed = (TextView) findViewById(R.id.wind_speed);
        wind_direction = (TextView) findViewById(R.id.wind_direction);
        sunrise_time = (TextView) findViewById(R.id.sunrise_time);
        sunset_time = (TextView) findViewById(R.id.sunset_time);
        min_temp = (TextView) findViewById(R.id.min_temp);
        max_temp = (TextView) findViewById(R.id.max_temp);
        donut_progress = (DonutProgress) findViewById(R.id.donut_progress);
        circleProMax = (CircleProgress) findViewById(R.id.circle_prog_max);
        circleProMin = (CircleProgress) findViewById(R.id.circle_prog_min);
        wind_dir = (ImageView) findViewById(R.id.wind_dir);
        condition_img = (ImageView) findViewById(R.id.condition_img);


        ApiHelper.getWeatherMap().enqueue(new Callback<WeatherMap>() {
            @Override
            public void onResponse(Call<WeatherMap> call, Response<WeatherMap> response) {
                if (response.isSuccessful()) {
                    WeatherMap wm = response.body();

                    //main data
                    city_name.setText(wm.getName());
                    description.setText("Current weather " + wm.getWeather().get(0).getDescription().toString() + ", pressure " + wm.getMain().getPressure() + " hpa.");
                    main_temp.setText(String.valueOf((int) wm.getMain().getTemp()) + "º");

                    //circleprogress
                    circleProMax.setMax(50);
                    circleProMax.setSuffixText("º");
                    circleProMin.setMax(50);
                    circleProMin.setSuffixText("º");

                    circleProMax.setProgress((int) wm.getMain().getTempMax());
                    circleProMin.setProgress((int) wm.getMain().getTempMin());
                    donut_progress.setProgress(wm.getMain().getHumidity());
                    if (wm.getMain().getTempMax()< 0){
                        circleProMax.setTextColor(Color.RED);
                        circleProMin.setTextColor(Color.RED);
                    }

                    //wind
                    wind_speed.setText("Speed " + String.valueOf(wm.getWind().getSpeed()) + " m/s");
                    wind_dir.setRotation((float) wm.getWind().getDeg());
                    if (wm.getWind().getDeg() > 315 || wm.getWind().getDeg() < 45) {
                        wind_direction.setText("North");
                    } else if (wm.getWind().getDeg() > 45 && wm.getWind().getDeg() < 135) {
                        wind_direction.setText("East ");
                    } else if (wm.getWind().getDeg() > 135 && wm.getWind().getDeg() < 225) {
                        wind_direction.setText("South");
                    } else if (wm.getWind().getDeg() > 225 && wm.getWind().getDeg() < 315) {
                        wind_direction.setText("West ");
                    }

                    //time
                    long unixTimeSunset = wm.getSys().getSunset();
                    long unixTimeSunrise = wm.getSys().getSunrise();
                    long javaTimeSunset = unixTimeSunset * 1000L;
                    long javaTimeSunrise = unixTimeSunrise * 1000L;
                    Date dateSunrise = new Date(javaTimeSunrise);
                    Date dateSunset = new Date(javaTimeSunset);
                    String sunriseStr = new SimpleDateFormat("HH:mm").format(dateSunrise);
                    String sunsetStr = new SimpleDateFormat("HH:mm").format(dateSunset);
                    sunset_time.setText("" + sunsetStr);
                    sunrise_time.setText("" + sunriseStr);

                    //weather
                    Log.d("MY_TAG", "1work");
                    switch (wm.getWeather().get(0).getDescription()) {
                        case "clear sky":
                            condition_img.setImageResource(R.drawable.clear_sky);
                            break;
                        case "few clouds":
                            condition_img.setImageResource(R.drawable.few_clouds);
                            break;
                        case "scattered clouds":
                            condition_img.setImageResource(R.drawable.scattered_clouds);
                            break;
                        case "broken clouds":
                            condition_img.setImageResource(R.drawable.broken_clouds);
                            break;
                        case "shower rain":
                            condition_img.setImageResource(R.drawable.shower_rain);
                            break;
                        case "rain":
                            condition_img.setImageResource(R.drawable.rain);
                            break;
                        case "thunderstorm":
                            condition_img.setImageResource(R.drawable.thunderstorm);
                            break;
                        case "snow":
                            condition_img.setImageResource(R.drawable.snow);
                            break;
                        case "mist":
                            condition_img.setImageResource(R.drawable.mist);
                            break;
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherMap> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Recuest Error", Toast.LENGTH_LONG).show();
            }
        });
    }


}
