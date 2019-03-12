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

public interface WeatherContract {

    interface ViewI extends IBaseView {

        void showWeather();

        void showError(String errorMsg);

        void showBasic(Basic basic);

        void showNow(Now now);

        void showForecast(Forecast forecast);

        void showAqi(AQI aqi);

        void showSuggestion(Suggestion suggestion);

        void showBackgroundPic();

        void openSelectCity();

    }

    interface PresenterI extends IBasePresenter {

    }
}
