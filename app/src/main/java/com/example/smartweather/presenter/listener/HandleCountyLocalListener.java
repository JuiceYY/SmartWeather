package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 14:53
 * DESCRIPTION:
 */

import com.example.smartweather.data.bean.County;

import java.util.List;

public interface HandleCountyLocalListener {

    void onSuccess(List<County> provinceList);

    void onFailed(int provinceId, int cityId);
}
