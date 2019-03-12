package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 13:19
 * DESCRIPTION:
 */

import com.example.smartweather.data.response.ProvinceResponse;

import java.util.List;

public interface HandleProvinceRemoteListener {

    void onSuccess(List<ProvinceResponse> provinceResponseList);

    void onFailed(String errorMsg);
}
