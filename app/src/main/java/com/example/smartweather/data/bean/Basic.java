package com.example.smartweather.data.bean;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:09
 * DESCRIPTION:
 */

public class Basic {
    private String city;
    private String cid;
    private Update update;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    class Update{
        private String loc;

        public String getUpdateTime() {
            return loc;
        }

        public void setUpdateTime(String updateTime) {
            this.loc = updateTime;
        }
    }
}
