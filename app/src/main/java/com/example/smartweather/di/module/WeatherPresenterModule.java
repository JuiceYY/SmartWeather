package com.example.smartweather.di.module;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 19:33
 * DESCRIPTION:
 */

import com.example.smartweather.contract.WeatherContract;
import com.example.smartweather.presenter.WeatherPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherPresenterModule {

    @Provides
    WeatherContract.Presenter provideWeatherPresenter(){
        return new WeatherPresenter();
    }
}
