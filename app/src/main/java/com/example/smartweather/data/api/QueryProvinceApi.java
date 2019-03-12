package com.example.smartweather.data.api;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:14
 * DESCRIPTION:
 */

import com.example.smartweather.data.response.ProvinceResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface QueryProvinceApi {

    @GET("api/china")
    Observable<List<ProvinceResponse>> queryProvince();
}
