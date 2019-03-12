package com.example.smartweather.presenter;

import com.example.smartweather.base.IBaseView;
import com.example.smartweather.contract.CityContract;
import com.example.smartweather.data.bean.City;
import com.example.smartweather.data.bean.County;
import com.example.smartweather.data.bean.Province;
import com.example.smartweather.data.model.local.LocalCityModel;
import com.example.smartweather.data.model.remote.RemoteCityModel;
import com.example.smartweather.data.response.CityResponse;
import com.example.smartweather.data.response.CountyResponse;
import com.example.smartweather.data.response.ProvinceResponse;
import com.example.smartweather.presenter.listener.HandleCityRemoteListener;
import com.example.smartweather.presenter.listener.HandleCountyRemoteListener;
import com.example.smartweather.presenter.listener.HandleProvinceRemoteListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 19:50
 * DESCRIPTION:
 */

public class CityPresenter implements CityContract.Presenter {

    private List<Province> mProvinceList;
    private List<City> mCityList;
    private List<County> mCountyList;
    private Province mSelectedProvince;
    private City mSelectedCity;
    private County mSelectedCounty;


    private LocalCityModel mLocalCityModel;
    private RemoteCityModel mRemoteCityModel;

    private CityContract.View mView;

    @Inject
    public CityPresenter(LocalCityModel localCityModel, RemoteCityModel remoteCityModel){
        this.mLocalCityModel = localCityModel;
        this.mRemoteCityModel = remoteCityModel;
    }

    @Override
    public void takeView(CityContract.View view) {
        this.mView = view;
    }

    @Override
    public void queryProvinces() {
        mProvinceList = mLocalCityModel.getProvinces();
        if(mProvinceList == null || mProvinceList.size() == 0){
            //本地无数据, 联网查询
            mRemoteCityModel.getProvinces(new HandleProvinceRemoteListener() {
                @Override
                public void onSuccess(List<ProvinceResponse> provinceResponseList) {
                    List<String> names = new ArrayList<>();
                    List<Province> provinceList = new ArrayList<>();
                    for(ProvinceResponse response : provinceResponseList){
                        names.add(response.getName());

                        Province province = new Province();
                        province.setProvinceCode(response.getId());
                        province.setProvinceName(response.getName());
                        provinceList.add(province);
                    }
                    mLocalCityModel.insertProvinces(provinceList);
                    mView.showNames(names);
                }

                @Override
                public void onFailed(String errorMsg) {
                    mView.showError(errorMsg);
                }
            });
        }else{
            //本地有数据
            List<String> names = new ArrayList<>();
            for (Province province: mProvinceList){
                names.add(province.getProvinceName());
            }
            mView.showNames(names);
        }
    }


    @Override
    public void queryCities(int clickPosition) {
        mSelectedProvince = mProvinceList.get(clickPosition);
        mCityList = mLocalCityModel.getCities(mSelectedProvince.getProvinceCode());
        if(mCityList == null || mCityList.size() == 0){
            mRemoteCityModel.getCities(mSelectedProvince.getId(), new HandleCityRemoteListener() {
                @Override
                public void onSuccess(List<CityResponse> cityResponseList) {
                    List<City> cityList = new ArrayList<>();
                    List<String> names = new ArrayList<>();
                    for (CityResponse response : cityResponseList){
                        City city = new City();
                        city.setCityCode(response.getId());
                        city.setCityName(response.getName());
                        city.setProvinceId(mSelectedProvince.getProvinceCode());
                        cityList.add(city);

                        names.add(response.getName());
                    }

                    mLocalCityModel.insertCities(cityList);
                    mView.showNames(names);
                }

                @Override
                public void onFailed(String errorMsg) {
                    mView.showError(errorMsg);
                }
            });
        }else {
            List<String> names = new ArrayList<>();
            for (City city : mCityList){
                names.add(city.getCityName());
            }
            mView.showNames(names);
        }
    }

    @Override
    public void queryCounties(int clickPosition) {
        mSelectedCity = mCityList.get(clickPosition);
        mCountyList = mLocalCityModel.getCounties(mSelectedCity.getProvinceId(), mSelectedCity.getCityCode());
        if(mCountyList == null || mCountyList.size() == 0){
            mRemoteCityModel.getCounties(mSelectedCity.getProvinceId(), mSelectedCity.getCityCode(), new HandleCountyRemoteListener() {
                @Override
                public void onSuccess(List<CountyResponse> countyResponseList) {
                    List<County> countyList = new ArrayList<>();
                    List<String> names = new ArrayList<>();
                    for (CountyResponse response : countyResponseList){
                        County county = new County();
                        county.setCityId(response.getId());
                        county.setCountyName(response.getName());
                        county.setWeatherId(response.getWeather_id());
                        countyList.add(county);
                        names.add(response.getName());
                    }
                    mLocalCityModel.insertCounties(countyList);
                    mView.showNames(names);
                }

                @Override
                public void onFailed(String errorMsg) {
                    mView.showError(errorMsg);
                }
            });

        }else {
            List<String> names = new ArrayList<>();
            for (County county : mCountyList){
                names.add(county.getCountyName());
            }
            mView.showNames(names);
        }
    }

    @Override
    public void startWeatherActivity(int clickPosition) {

    }

}
