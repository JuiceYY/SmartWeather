package com.example.smartweather.net.retrofit;
/*
 * CREATED BY: Sinry
 * TIME: 2019/2/2 19:16
 * DESCRIPTION:
 */

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyHttpClient {
    //单例模式

    private static volatile MyHttpClient mInstance;

    private OkHttpClient okHttpClient;

    private MyHttpClient(){
        initOkhttpClient();
    }

    public static MyHttpClient getInstance(){
        if(mInstance == null){
            synchronized (MyHttpClient.class){
                if(mInstance == null){
                    mInstance = new MyHttpClient();
                }
            }
        }

        return mInstance;
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    private void initOkhttpClient(){

        //打印请求日志的拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new RequestInterceptor()) //添加公共头部的拦截器
                .build();
    }

    private static class RequestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = addGetDefaultParams(chain);
            return chain.proceed(request);
        }
    }

    private static Request addGetDefaultParams(Interceptor.Chain chain){
        Request request = chain.request();

        //TODO 这里可以对request添加默认参数, 然后返回, 这里就不做处理了

        return request;
    }


}
