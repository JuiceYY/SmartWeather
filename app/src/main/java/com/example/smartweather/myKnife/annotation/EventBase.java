package com.example.smartweather.myKnife.annotation;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/2 10:12
 * DESCRIPTION:
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {

    //对应setOn...Listener()方法, 监听方法
    String listenerSetter();

    //对应View.On...Listener对象, 监听的对象
    Class<?> listenerType();

    //对应回调的方法名 onClick()
    String callbackMethod();

}
