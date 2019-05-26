package com.wzk.mebatis.v1;

import javax.swing.*;
import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

public class WZKConfiguration {

    public static final ResourceBundle sqlMappings;

    static {
        sqlMappings = ResourceBundle.getBundle("v1sql");
    }

    public <T> T getMapper(Class clazz, WZKSqlSession wzkSqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new WZKMapperProxy(wzkSqlSession));
    }
}
