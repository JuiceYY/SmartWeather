package com.example.smartweather.myKnife.annotation;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/2 12:13
 * DESCRIPTION:
 */

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerSetter = "setOnLongClickListener", listenerType = View.OnLongClickListener.class, callbackMethod = "onLongClick")
public @interface OnLongClick {

    int[] value();
}
