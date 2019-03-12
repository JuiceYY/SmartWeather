package com.example.smartweather.data.model.remote;

import com.example.smartweather.base.Constant;
import com.example.smartweather.data.api.QueryWeatherApi;
import com.example.smartweather.data.response.WeatherResponse;
import com.example.smartweather.net.retrofit.RetrofitHelper;
import com.example.smartweather.net.retrofit.ThreadChanger;
import com.example.smartweather.presenter.listener.HandleWeatherDataListener;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:30
 * DESCRIPTION:
 */

public class RemoteWeatherModel {

    public void getWeather(String weather_id, final HandleWeatherDataListener listener) {
        RetrofitHelper.getInstance()
                .createApi(QueryWeatherApi.class, Constant.DOMAIN)
                .queryWeather(weather_id, Constant.KEY)
                .compose(ThreadChanger.<WeatherResponse>io2main())
                .subscribe(new BaseObserver<WeatherResponse>() {
                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        listener.onSuccess(weatherResponse);
                    }

                    @Override
                    protected void onNetError(Throwable e) {
                        listener.onFailed(e.getMessage());
                    }
                });

    }
}
