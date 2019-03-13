package com.example.smartweather.data.bean;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 21:53
 * DESCRIPTION:
 */

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class City {

    @Id
    private long cityCode;
    private String cityName;
    private int provinceCode;
    @Generated(hash = 1677850844)
    public City(long cityCode, String cityName, int provinceCode) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.provinceCode = provinceCode;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public long getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(long cityCode) {
        this.cityCode = cityCode;
    }
    public int getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }




}
