package com.example.smartweather.data.model.remote;

import com.example.smartweather.base.Constant;
import com.example.smartweather.data.api.LoadBingPicApi;
import com.example.smartweather.data.api.QueryWeatherApi;
import com.example.smartweather.data.response.WeatherResponse;
import com.example.smartweather.net.retrofit.MyHttpClient;
import com.example.smartweather.net.retrofit.RetrofitHelper;
import com.example.smartweather.net.retrofit.ThreadChanger;
import com.example.smartweather.presenter.listener.HandlePicListener;
import com.example.smartweather.presenter.listener.HandleWeatherDataListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:30
 * DESCRIPTION:
 */

public class RemoteWeatherModel {


    public void getWeather(final String weather_id, final HandleWeatherDataListener listener) {
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

    public void getBingPic(final HandlePicListener listener){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.DOMAIN)
                .client(MyHttpClient.getInstance().getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        retrofit.create(LoadBingPicApi.class)
                .getBingPic()
                .compose(ThreadChanger.<String>io2main())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        listener.onSuccess(s);
                    }
                });

    }
}
