package com.example.smartweather.di.component;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 19:36
 * DESCRIPTION:
 */


import com.example.smartweather.di.module.WeatherModelModule;
import com.example.smartweather.presenter.WeatherPresenter;

import dagger.Component;

@Component(modules = WeatherModelModule.class)
public interface WeatherModelComponent {

    void inject(WeatherPresenter presenter);
}
