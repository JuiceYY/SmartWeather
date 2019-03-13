package com.example.smartweather.data.model.remote;

import com.example.smartweather.base.Constant;
import com.example.smartweather.data.api.LoadBingPicApi;
import com.example.smartweather.data.api.QueryWeatherApi;
import com.example.smartweather.data.response.WeatherResponse;
import com.example.smartweather.net.retrofit.RetrofitHelper;
import com.example.smartweather.net.retrofit.ThreadChanger;
import com.example.smartweather.presenter.listener.HandlePicListener;
import com.example.smartweather.presenter.listener.HandleWeatherDataListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:30
 * DESCRIPTION:
 */

public class RemoteWeatherModel {

    private ExecutorService mExecutors;

    public RemoteWeatherModel(){
        mExecutors = Executors.newCachedThreadPool();
    }

    public void getWeather(final String weather_id, final HandleWeatherDataListener listener) {
        mExecutors.execute(new Runnable() {
            @Override
            public void run() {
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
        });


    }

    public void getBingPic(final HandlePicListener listener){

        mExecutors.execute(new Runnable() {
            @Override
            public void run() {
                RetrofitHelper.getInstance()
                        .createApi(LoadBingPicApi.class, Constant.DOMAIN)
                        .getBingPic()
                        .compose(ThreadChanger.<String>io2main())
                        .subscribe(new BaseObserver<String>() {
                            @Override
                            public void onNext(String picUrl) {
                                listener.onSuccess(picUrl);
                            }

                            @Override
                            protected void onNetError(Throwable e) {
                                listener.onFailed(e.getMessage());
                            }
                        });
            }
        });

    }
}
