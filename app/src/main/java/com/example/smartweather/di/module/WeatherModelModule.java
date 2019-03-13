package com.example.smartweather.di.module;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 19:50
 * DESCRIPTION:
 */

import com.example.smartweather.data.model.remote.RemoteWeatherModel;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModelModule {

    @Provides
    RemoteWeatherModel provideRemoteWeatherModel(){
        return new RemoteWeatherModel();
    }
}
