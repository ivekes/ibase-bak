package com.wzk.singleton.hungry;

/**
 * 一般饿汉式
 */
public class HungrySingleton {

    private HungrySingleton(){}

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
