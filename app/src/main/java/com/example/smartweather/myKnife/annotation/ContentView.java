package com.example.smartweather.myKnife.annotation;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/1 23:48
 * DESCRIPTION:
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {

    int value();

}
