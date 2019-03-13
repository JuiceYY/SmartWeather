package com.example.smartweather.contract;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 16:45
 * DESCRIPTION:
 */

import com.example.smartweather.base.IBasePresenter;
import com.example.smartweather.base.IBaseView;
import com.example.smartweather.data.bean.AQI;
import com.example.smartweather.data.bean.Basic;
import com.example.smartweather.data.bean.Forecast;
import com.example.smartweather.data.bean.Now;
import com.example.smartweather.data.bean.Suggestion;
import com.example.smartweather.data.response.WeatherResponse;

import java.util.List;

public interface WeatherContract {

    interface View extends IBaseView {

        void showWeather();

        void showBasic(Basic basic);

        void showNow(Now now);

        void showForecast(List<Forecast> forecastList);

        void showAqi(AQI aqi);

        void showSuggestion(Suggestion suggestion);

        void showError(String errorMsg);

        void showBackgroundPic(String picUrl);

        void showLoading();

        void closeLoading();

    }

    interface Presenter extends IBasePresenter {

        void takeView(View view);

        void startAutoUpdateService();

        void queryWeather(String weatherId);

        void getBackgroundPic();


    }
}
