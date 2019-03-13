package com.example.smartweather.di.component;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 22:31
 * DESCRIPTION:
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartweather.base.BaseApplication;
import com.example.smartweather.data.bean.DaoSession;
import com.example.smartweather.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    BaseApplication getApplication();
    DaoSession getDaoSession();
    SharedPreferences getSharedPreference();

}
