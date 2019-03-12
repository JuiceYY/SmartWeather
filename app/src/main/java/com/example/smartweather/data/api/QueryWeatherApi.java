package com.example.smartweather.data.api;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:42
 * DESCRIPTION:
 */


import com.example.smartweather.data.response.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QueryWeatherApi {

    @GET("api/weather")
    Observable<WeatherResponse> queryWeather(@Query("cityid") String cityid,
                                             @Query("key") String key);

}
