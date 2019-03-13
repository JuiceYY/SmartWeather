package com.example.smartweather.data.response;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:54
 * DESCRIPTION:
 */

import com.example.smartweather.data.bean.HeWeather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("HeWeather")
    private List<HeWeather> heWeather;

    public List<HeWeather> getHeWeather() {
        return heWeather;
    }

    public void setHeWeather(List<HeWeather> heWeather) {
        this.heWeather = heWeather;
    }
}
