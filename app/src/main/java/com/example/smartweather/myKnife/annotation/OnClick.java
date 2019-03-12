package com.example.smartweather.myKnife.annotation;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/2 0:30
 * DESCRIPTION:
 */

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class, callbackMethod = "onClick")
public @interface OnClick {

    int[] value();
}
