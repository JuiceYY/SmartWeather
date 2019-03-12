package com.example.smartweather.data.api;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:58
 * DESCRIPTION:
 */

import com.example.smartweather.data.response.CityResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QueryCitiesApi {
    @GET("api/china/{provinceId}")
    Observable<List<CityResponse>> queryCities(@Path("provinceId") String provinceId);
}
