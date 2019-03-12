package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 14:52
 * DESCRIPTION:
 */

import com.example.smartweather.data.bean.Province;

import java.util.List;

public interface HandleProvinceLocalListener {
    void onSuccess(List<Province> provinceList);

    void onFailed();
}
