package com.example.smartweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 12694 on 2017/10/1.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
