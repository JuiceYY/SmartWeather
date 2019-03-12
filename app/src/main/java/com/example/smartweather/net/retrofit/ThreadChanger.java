package com.example.smartweather.net.retrofit;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 20:20
 * DESCRIPTION:
 */

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ThreadChanger {

    private ThreadChanger(){

    }

    public static <T>ObservableTransformer<T, T> io2main(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        //上层在io线程
                        .subscribeOn(Schedulers.io())
                        //下层在主线程
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
