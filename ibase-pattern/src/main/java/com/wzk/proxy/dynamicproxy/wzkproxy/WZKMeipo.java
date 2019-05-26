package com.wzk.proxy.dynamicproxy.wzkproxy;

import java.lang.reflect.Method;

public class WZKMeipo implements WZKInvocationHandler{

    private Object target;//目标对象

    public Object getInstance(Object target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return WZKProxy.newProxyInstance(new WZKClassLoader(),clazz.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(target.getClass(), args);
        after();
        return obj;
    }

    private void before(){
        System.out.println("我是媒婆，确认需求，开始找对象");
    }

    private void after(){
        System.out.println("同意的话，开始办事");
    }
}
