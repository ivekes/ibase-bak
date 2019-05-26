package com.wzk.proxy;

import com.wzk.proxy.dynamicproxy.jdkproxy.Girl;
import com.wzk.proxy.dynamicproxy.jdkproxy.JDKMeipo;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JDKMeipoTest {

    @Test
    public void test(){
        Object obj = new JDKMeipo().getInstance(new Girl());
        try {
            Method method = obj.getClass().getMethod("findLove",null);
            method.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
