package com.example.smartweather.di.module;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 13:31
 * DESCRIPTION:
 */

import com.example.smartweather.data.bean.DaoSession;
import com.example.smartweather.data.model.local.LocalCityModel;
import com.example.smartweather.data.model.remote.RemoteCityModel;
import com.example.smartweather.di.scope.CityModelScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CityModelModule {

    private DaoSession mDaoSession;

    public CityModelModule(DaoSession daoSession){
        this.mDaoSession = daoSession;
    }

    @CityModelScope
    @Provides
    LocalCityModel provideLocalCityModel(){
        return new LocalCityModel(mDaoSession);
    }

    @CityModelScope
    @Provides
    RemoteCityModel provideRemoteCityModel(){
        return new RemoteCityModel();
    }

}
