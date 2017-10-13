package com.example.smartweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 12694 on 17-10-13.
 */

public class Weather {

    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
