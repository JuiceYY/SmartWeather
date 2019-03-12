package com.example.smartweather.utils;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:30
 * DESCRIPTION:
 */

import android.util.Log;

public class MyLog {

    private static final boolean DEBUG = true;

    private static final String TAG = "SmartWeatherDebug";

    public static void d(String text){
        if(DEBUG){
            Log.d(TAG, text);
        }
    }
    public static void i(String text){
        if(DEBUG){
            Log.i(TAG, text);
        }
    }
    public static void w(String text){
        if(DEBUG){
            Log.w(TAG, text);
        }
    }
    public static void e(String text){
        if(DEBUG){
            Log.e(TAG, text);
        }
    }
}
