package com.example.smartweather.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

import com.example.smartweather.data.bean.DaoMaster;
import com.example.smartweather.data.bean.DaoSession;
import com.example.smartweather.di.component.ApplicationComponent;
import com.example.smartweather.di.component.DaggerApplicationComponent;
import com.example.smartweather.di.module.ApplicationModule;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 21:51
 * DESCRIPTION:
 */

public class BaseApplication extends Application {

    private DaoSession mDaoSession;
    private SharedPreferences mSp;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
        initDagger();
        initSharedPreference();
    }

    private void initSharedPreference() {
        mSp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void initDagger() {
        ApplicationComponent component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, mDaoSession, mSp))
                .build();
        ComponentHolder.setAooComponent(component);
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "city.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static class ComponentHolder{
        private static ApplicationComponent mComponent;
        public static void setAooComponent(ApplicationComponent component){
            mComponent = component;
        }
        public static ApplicationComponent getAppComponent(){
            return mComponent;
        }

    }

}
