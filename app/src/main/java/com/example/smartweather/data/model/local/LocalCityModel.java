package com.example.smartweather.data.model.local;

import com.example.smartweather.data.bean.City;
import com.example.smartweather.data.bean.County;
import com.example.smartweather.data.bean.DaoSession;
import com.example.smartweather.data.bean.Province;
import com.example.smartweather.presenter.listener.HandleCityLocalListener;
import com.example.smartweather.presenter.listener.HandleCountyLocalListener;
import com.example.smartweather.presenter.listener.HandleProvinceLocalListener;

import java.util.List;

import javax.inject.Inject;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:29
 * DESCRIPTION: 本地获取城市列表信息
 */

public class LocalCityModel {

    @Inject
    DaoSession mDaoSession;

    @Inject
    public LocalCityModel(DaoSession daoSession){
        this.mDaoSession = daoSession;
    }

    public List<Province> getProvinces() {
        return mDaoSession.getProvinceDao()
                .queryBuilder()
                .list();
    }

    public List<City> getCities(int provinceId) {
        return mDaoSession.getCityDao()
                .queryBuilder()
                .list();
    }

    public List<County> getCounties(int provinceId, int cityId) {
         return mDaoSession.getCountyDao()
                .queryBuilder()
                .list();
    }

    public void insertProvinces(List<Province> provinces){
        for (Province province : provinces){
            mDaoSession.getProvinceDao().insert(province);
        }
    }

    public void insertCities(List<City> cities){
        for (City city: cities) {
            mDaoSession.getCityDao().insert(city);
        }
    }

    public void insertCounties(List<County> counties){
        for (County county: counties){
            mDaoSession.getCountyDao().insert(county);
        }
    }
}
