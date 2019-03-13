package com.example.smartweather.presenter.listener;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/13 21:52
 * DESCRIPTION:
 */

public interface HandlePicListener {

    void onSuccess(String picUrl);

    void onFailed(String errorMsg);
}
