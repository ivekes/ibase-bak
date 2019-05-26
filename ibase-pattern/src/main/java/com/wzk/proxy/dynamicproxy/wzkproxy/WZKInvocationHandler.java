package com.wzk.proxy.dynamicproxy.wzkproxy;

import java.lang.reflect.Method;

public interface WZKInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
