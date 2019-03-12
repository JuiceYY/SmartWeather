package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 13:19
 * DESCRIPTION:
 */

import com.example.smartweather.data.response.CountyResponse;

import java.util.List;

public interface HandleCountyRemoteListener {

    void onSuccess(List<CountyResponse> countyResponseList);

    void onFailed(String errorMsg);
}
