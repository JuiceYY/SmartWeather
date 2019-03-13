package com.example.smartweather.di.module;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 13:26
 * DESCRIPTION:
 */

import com.example.smartweather.contract.CityContract;
import com.example.smartweather.presenter.CityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CityPresenterModule {

    @Provides
    CityContract.Presenter provideCityPresenter(){
        return new CityPresenter();
    }
}
