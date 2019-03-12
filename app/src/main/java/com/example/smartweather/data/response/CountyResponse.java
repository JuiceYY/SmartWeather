package com.example.smartweather.data.response;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:10
 * DESCRIPTION:
 */

public class CountyResponse {

    private int id;
    private String name;
    private String weather_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }
}
