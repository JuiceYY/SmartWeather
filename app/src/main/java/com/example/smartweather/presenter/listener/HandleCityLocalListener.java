package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 14:52
 * DESCRIPTION:
 */

import com.example.smartweather.data.bean.City;

import java.util.List;

public interface HandleCityLocalListener{

    void onSuccess(List<City> provinceList);

    void onFailed(int provinceId);
}
