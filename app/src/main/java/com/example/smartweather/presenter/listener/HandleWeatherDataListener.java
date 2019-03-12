package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 13:13
 * DESCRIPTION:
 */

import com.example.smartweather.data.response.WeatherResponse;

public interface HandleWeatherDataListener {

    void onSuccess(WeatherResponse response);

    void onFailed(String errorMsg);

}
