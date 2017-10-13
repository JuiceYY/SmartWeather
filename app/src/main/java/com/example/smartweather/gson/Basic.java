package com.example.smartweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 12694 on 17-10-13.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;
    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }

}


