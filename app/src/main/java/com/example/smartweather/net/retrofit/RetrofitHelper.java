package com.example.smartweather.net.retrofit;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 20:38
 * DESCRIPTION:
 */

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static volatile RetrofitHelper instance;

    private MyHttpClient client;

    private RetrofitHelper(){
        client = MyHttpClient.getInstance();
    }

    public static RetrofitHelper getInstance(){
        if(null == instance){
            synchronized (RetrofitHelper.class){
                if(null == instance){
                    instance = new RetrofitHelper();
                }
            }
        }

        return instance;
    }



    /**
     * 构建retrofit
     * @param tClass 要构建的Api类型
     * @param baseUrl 请求域名
     * @param <T> 传入的Api类型
     * @return
     */
    public <T> T createApi(Class<T> tClass, String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(tClass);

    }


}
