package com.example.smartweather.data.model.remote;

import android.util.Log;

import com.example.smartweather.utils.MyLog;

import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/11 22:25
 * DESCRIPTION:
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        MyLog.d("Observer onSubscribe");
    }

    @Override
    public abstract void onNext(T t);

    @Override
    public void onError(Throwable e) {
        if(e instanceof ConnectException){
            onNetError(e);
        }
        MyLog.e("非网络错误, 无法处理" + e.getMessage());
    }

    protected abstract void onNetError(Throwable e);

    @Override
    public void onComplete() {
        MyLog.d("Observer onComplete");
    }
}
