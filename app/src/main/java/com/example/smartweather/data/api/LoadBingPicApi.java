package com.example.smartweather.data.api;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 21:50
 * DESCRIPTION:
 */

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface LoadBingPicApi {

    @GET("api/bing_pic")
    Observable<String> getBingPic();
}
