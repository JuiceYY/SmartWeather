package com.example.smartweather.data.bean;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:11
 * DESCRIPTION:
 */

public class Now {
    private String tmp;
    private More cond;

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public More getCond() {
        return cond;
    }

    public void setCond(More cond) {
        this.cond = cond;
    }

    public class More{
        private String txt;

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }
}
