package com.example.smartweather.myKnife;
/*
 * CREATED BY: Sinry
 * TIME: 2019/3/1 23:41
 * DESCRIPTION:
 */

import android.app.Activity;
import android.view.View;

import com.example.smartweather.myKnife.annotation.BindView;
import com.example.smartweather.myKnife.annotation.ContentView;
import com.example.smartweather.myKnife.annotation.EventBase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LittleKnife {

    private static volatile LittleKnife instance;

    private LittleKnife(){

    }

    public static LittleKnife getInstance(){
        if(null == instance){
            synchronized (LittleKnife.class){
                if(null == instance){
                    instance = new LittleKnife();
                }
            }
        }
        return instance;
    }

    public void inject(Activity activity){
        injectContentView(activity);
        injectView(activity);
        injectEvent(activity);
    }


    private void injectContentView(Activity activity){
        Class<?> clazz = activity.getClass();
        ContentView contentViewAnnotation = clazz.getAnnotation(ContentView.class);
        if(contentViewAnnotation != null){
            int layoutId = contentViewAnnotation.value();

            //layout  setContentView()  activity
            try {
                Method setContentViewMethod = clazz.getMethod("setContentView", int.class);
                setContentViewMethod.invoke(activity, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    private void injectView(Activity activity){
        Class<?> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            BindView bindViewAnnotation = field.getAnnotation(BindView.class);
            if(bindViewAnnotation != null){
                int viewId = bindViewAnnotation.value();

                //value, field, findViewbyid()
                try {
                    Method findViewByIdMethod = clazz.getMethod("findViewById", int.class);
                    View view = (View) findViewByIdMethod.invoke(activity, viewId);
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void injectEvent(Activity activity){

        Class<?> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods){
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations){//这个annotation是OnClick
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if(annotationType != null){
                    EventBase eventBaseAnnotation = annotationType.getAnnotation(EventBase.class);
                    if(eventBaseAnnotation != null){
                        //走到这里说明这个Method是被我们的事件注解注解的
                        String listenerSetter = eventBaseAnnotation.listenerSetter();
                        Class<?> listenerType = eventBaseAnnotation.listenerType();
                        String callbackMethod = eventBaseAnnotation.callbackMethod();

                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity, callbackMethod, method);

                        //使用myListener代理了listenerType的实例, 之后所有listenerType的方法都会交给handler的invoke() 方法处理
                        Object myListener = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, handler);

                        try {
                            Method valueMethod = annotationType.getMethod("value");
                            int[] viewIds = (int[]) valueMethod.invoke(annotation);
                            //先把viewid在这里findviewbyid一下
                            for(int viewId : viewIds){
                                Method findViewByIdMethod = clazz.getMethod("findViewById", int.class);
                                View view = (View) findViewByIdMethod.invoke(activity, viewId);

                                //再拿setOnClickListener方法
                                Method setterMethod = view.getClass().getMethod(listenerSetter, listenerType);
                                //执行自己的listener
                                setterMethod.invoke(view, myListener);
                            }


                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }


}
