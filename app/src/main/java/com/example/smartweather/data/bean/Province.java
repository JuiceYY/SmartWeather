package com.example.smartweather.data.bean;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 21:54
 * DESCRIPTION:
 */

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Province {

    @Id
    private long provinceCode;
    private String provinceName;
    @Generated(hash = 1810191997)
    public Province(long provinceCode, String provinceName) {
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }
    @Generated(hash = 1309009906)
    public Province() {
    }
    public long getProvinceCode() {
        return this.provinceCode;
    }
    public void setProvinceCode(long provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


}
