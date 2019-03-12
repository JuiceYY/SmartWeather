package com.example.smartweather.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartweather.data.bean.DaoMaster;
import com.example.smartweather.data.bean.DaoSession;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 21:51
 * DESCRIPTION:
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "city.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return getDaoSession();
    }

}
