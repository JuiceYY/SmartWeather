package com.example.smartweather.data.api;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 23:15
 * DESCRIPTION:
 */

import com.example.smartweather.data.response.CountyResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QueryCountiesApi {

    @GET("api/china/{provinceId}/{cityId}")
    Observable<List<CountyResponse>> queryCounties(@Path("provinceId") String provinceId,
                                                   @Path("cityId") String cityId);
}
