package com.example.smartweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 12694 on 17-10-13.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }


}
