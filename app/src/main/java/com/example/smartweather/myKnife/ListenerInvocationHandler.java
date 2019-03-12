package com.example.smartweather.myKnife;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/2 11:31
 * DESCRIPTION: 偷天换日: 本该执行的方法callbackMethod <==> ListenerInvocationHandler <==> 自己使用注解的方法
 *
 */

public class ListenerInvocationHandler implements InvocationHandler {

    private Object mTarget; //真正执行方法的对象
    private String mMethodName; //要被替代的方法
    private Method mRealMethod; //真正执行的方法

    public ListenerInvocationHandler(Object obj, String methodName, Method realMethod){
        this.mTarget = obj;
        this.mMethodName = methodName;
        this.mRealMethod = realMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(mTarget != null){
            if(mMethodName.equals(method.getName())){
                return mRealMethod.invoke(mTarget, args);
            }
        }
        return null;
    }

}
