package com.example.smartweather.data.model.remote;

import com.example.smartweather.base.Constant;
import com.example.smartweather.data.api.QueryCitiesApi;
import com.example.smartweather.data.api.QueryCountiesApi;
import com.example.smartweather.data.api.QueryProvinceApi;
import com.example.smartweather.data.response.CityResponse;
import com.example.smartweather.data.response.CountyResponse;
import com.example.smartweather.data.response.ProvinceResponse;
import com.example.smartweather.net.retrofit.RetrofitHelper;
import com.example.smartweather.net.retrofit.ThreadChanger;
import com.example.smartweather.presenter.listener.HandleCityRemoteListener;
import com.example.smartweather.presenter.listener.HandleCountyRemoteListener;
import com.example.smartweather.presenter.listener.HandleProvinceRemoteListener;

import java.util.List;

import javax.inject.Inject;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:31
 * DESCRIPTION:
 */

public class RemoteCityModel {

    @Inject
    public RemoteCityModel(){

    }

    public void getProvinces(final HandleProvinceRemoteListener listener) {
        RetrofitHelper.getInstance()
                .createApi(QueryProvinceApi.class, Constant.DOMAIN)
                .queryProvince()
                .compose(ThreadChanger.<List<ProvinceResponse>>io2main())
                .subscribe(new BaseObserver<List<ProvinceResponse>>() {
                    @Override
                    public void onNext(List<ProvinceResponse> provinceResponses) {
                        listener.onSuccess(provinceResponses);
                    }

                    @Override
                    protected void onNetError(Throwable e) {
                        listener.onFailed(e.getMessage());
                    }
                });
    }

    public void getCities(int provinceId, final HandleCityRemoteListener listener) {
        RetrofitHelper.getInstance()
                .createApi(QueryCitiesApi.class, Constant.DOMAIN)
                .queryCities(String.valueOf(provinceId))
                .compose(ThreadChanger.<List<CityResponse>>io2main())
                .subscribe(new BaseObserver<List<CityResponse>>() {
                    @Override
                    public void onNext(List<CityResponse> cityResponses) {
                        listener.onSuccess(cityResponses);
                    }

                    @Override
                    protected void onNetError(Throwable e) {
                        listener.onFailed(e.getMessage());
                    }
                });

    }

    public void getCounties(int provinceId, int cityId, final HandleCountyRemoteListener listener) {
        RetrofitHelper.getInstance()
                .createApi(QueryCountiesApi.class, Constant.DOMAIN)
                .queryCounties(String.valueOf(provinceId), String.valueOf(cityId))
                .compose(ThreadChanger.<List<CountyResponse>>io2main())
                .subscribe(new BaseObserver<List<CountyResponse>>() {
                    @Override
                    public void onNext(List<CountyResponse> countyResponses) {
                        listener.onSuccess(countyResponses);
                    }

                    @Override
                    protected void onNetError(Throwable e) {
                        listener.onFailed(e.getMessage());
                    }
                });
    }
}
