package com.example.smartweather.myKnife.annotation;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/2 0:13
 * DESCRIPTION:
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {

    int value();

}
