package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 13:19
 * DESCRIPTION:
 */

import com.example.smartweather.data.response.CityResponse;

import java.util.List;

public interface HandleCityRemoteListener {

    void onSuccess(List<CityResponse> cityResponseList);

    void onFailed(String errorMsg);
}
