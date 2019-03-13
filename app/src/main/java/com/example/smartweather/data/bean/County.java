package com.example.smartweather.data.bean;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 21:53
 * DESCRIPTION:
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class County {

    @Id
    private long CountyId;
    private String countyName;
    private String weatherId;
    private  int cityCode;
    @Generated(hash = 1384089304)
    public County(long CountyId, String countyName, String weatherId,
            int cityCode) {
        this.CountyId = CountyId;
        this.countyName = countyName;
        this.weatherId = weatherId;
        this.cityCode = cityCode;
    }
    @Generated(hash = 1991272252)
    public County() {
    }
    public long getCountyId() {
        return this.CountyId;
    }
    public void setCountyId(long CountyId) {
        this.CountyId = CountyId;
    }
    public String getCountyName() {
        return this.countyName;
    }
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    public String getWeatherId() {
        return this.weatherId;
    }
    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
    public int getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }


}
