package com.example.smartweather.di.module;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 22:14
 * DESCRIPTION:
 */

import android.content.Context;

import com.example.smartweather.base.BaseApplication;
import com.example.smartweather.data.bean.DaoSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final BaseApplication mApplication;
    private final DaoSession mDaoSession;

    public ApplicationModule(BaseApplication application, DaoSession daoSession){
        this.mApplication = application;
        this.mDaoSession = daoSession;
    }

    @Singleton
    @Provides
    BaseApplication provideApplicationContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(){
        return mDaoSession;
    }

}
