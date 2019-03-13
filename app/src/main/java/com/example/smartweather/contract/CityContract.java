package com.example.smartweather.contract;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 18:55
 * DESCRIPTION:
 */

import com.example.smartweather.base.IBasePresenter;
import com.example.smartweather.base.IBaseView;

import java.util.List;

public interface CityContract {

    interface View extends IBaseView {
        void showNames(List<String> cities);
        void showError(String errorMsg);

        void startWeatherActivity(String weatherId);

        void showLoading();

        void closeLoading();
    }

    interface Presenter extends IBasePresenter {

        void takeView(View view);

        void queryProvinces();

        void queryCities(int clickPosition);

        void queryCounties(int clickPosition);

        void startWeatherActivity(int clickPosition);
    }
}
