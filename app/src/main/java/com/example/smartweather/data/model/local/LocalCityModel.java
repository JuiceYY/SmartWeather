package com.example.smartweather.data.model.local;

import com.example.smartweather.data.bean.City;
import com.example.smartweather.data.bean.CityDao;
import com.example.smartweather.data.bean.County;
import com.example.smartweather.data.bean.CountyDao;
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

    private DaoSession mDaoSession;

    public LocalCityModel(DaoSession daoSession) {
        this.mDaoSession = daoSession;
    }


    public List<Province> getProvinces() {
        return mDaoSession.getProvinceDao()
                .queryBuilder()
                .list();
    }

    public List<City> getCities(int provinceCode) {
        CityDao cityDao = mDaoSession.getCityDao();
        return cityDao.queryBuilder()
                .where(CityDao.Properties.ProvinceCode.eq(provinceCode))
                .list();
    }

    public List<County> getCounties(int provinceCode, int cityCode) {
        CountyDao countyDao = mDaoSession.getCountyDao();
        return countyDao.queryBuilder()
                .where(CountyDao.Properties.CityCode.eq(cityCode))
                .list();
    }

    public void insertProvinces(Province province) {
        mDaoSession.getProvinceDao().insert(province);

    }

    public void insertCities(City city) {
        mDaoSession.getCityDao().insert(city);
    }

    public void insertCounties(County county) {
            mDaoSession.getCountyDao().insert(county);
    }
}
