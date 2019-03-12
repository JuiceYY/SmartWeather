package com.example.smartweather.data.bean;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:09
 * DESCRIPTION:
 */

public class AQI {
    private AQICity city;

    public AQICity getCity() {
        return city;
    }

    public void setCity(AQICity city) {
        this.city = city;
    }

    class  AQICity{
        private String aqi;
        private String pm25;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }
    }
}
