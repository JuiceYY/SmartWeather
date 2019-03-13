package com.example.smartweather.presenter;

import com.example.smartweather.contract.WeatherContract;
import com.example.smartweather.data.bean.AQI;
import com.example.smartweather.data.bean.Basic;
import com.example.smartweather.data.bean.Forecast;
import com.example.smartweather.data.bean.HeWeather;
import com.example.smartweather.data.bean.Now;
import com.example.smartweather.data.bean.Suggestion;
import com.example.smartweather.data.model.remote.RemoteWeatherModel;
import com.example.smartweather.data.response.WeatherResponse;
import com.example.smartweather.di.component.DaggerWeatherModelComponent;
import com.example.smartweather.di.module.WeatherModelModule;
import com.example.smartweather.presenter.listener.HandlePicListener;
import com.example.smartweather.presenter.listener.HandleWeatherDataListener;

import java.util.List;

import javax.inject.Inject;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 17:32
 * DESCRIPTION:
 */

public class WeatherPresenter implements WeatherContract.Presenter {

    @Inject
    RemoteWeatherModel mWeatherModel;

    private WeatherContract.View mView;

    public WeatherPresenter(){
        DaggerWeatherModelComponent.builder()
                .weatherModelModule(new WeatherModelModule())
                .build()
                .inject(this);
    }

    @Override
    public void takeView(WeatherContract.View view) {
        this.mView = view;
    }

    @Override
    public void startAutoUpdateService() {

    }

    @Override
    public void queryWeather(String weatherId) {

        mView.showLoading();

        mWeatherModel.getWeather(weatherId, new HandleWeatherDataListener() {
            @Override
            public void onSuccess(WeatherResponse response) {
                HeWeather heWeather = response.getHeWeather().get(0);
                if(heWeather.getStatus().equals("ok")){
                    mView.closeLoading();
                    mView.showWeather();
                    Basic basic = heWeather.getBasic();
                    List<Forecast> forecastList = heWeather.getDaily_forecast();
                    Now now = heWeather.getNow();
                    Suggestion suggestion = heWeather.getSuggestion();
                    AQI aqi = heWeather.getAqi();

                    mView.showBasic(basic);
                    mView.showForecast(forecastList);
                    mView.showNow(now);
                    mView.showSuggestion(suggestion);
                    mView.showAqi(aqi);
                }else {
                    mView.showError("response status != ok!");
                }

            }

            @Override
            public void onFailed(String errorMsg) {
                mView.showError(errorMsg);
            }
        });

    }

    @Override
    public void getBackgroundPic() {
        mWeatherModel.getBingPic(new HandlePicListener() {
            @Override
            public void onSuccess(String picUrl) {
                mView.showBackgroundPic(picUrl);
            }

            @Override
            public void onFailed(String errorMsg) {
                mView.showError(errorMsg);
            }
        });
    }
}
