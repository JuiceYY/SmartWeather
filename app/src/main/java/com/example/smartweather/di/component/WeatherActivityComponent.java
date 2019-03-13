package com.example.smartweather.di.component;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 19:35
 * DESCRIPTION:
 */


import com.example.smartweather.di.module.WeatherPresenterModule;
import com.example.smartweather.view.activity.WeatherActivity;

import dagger.Component;

@Component(modules = WeatherPresenterModule.class)
public interface WeatherActivityComponent {

    void inject(WeatherActivity activity);
}
