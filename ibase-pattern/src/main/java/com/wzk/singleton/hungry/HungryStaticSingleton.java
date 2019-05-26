package com.wzk.singleton.hungry;

/**
 * 静态代码块 new
 */
public class HungryStaticSingleton {

    private HungryStaticSingleton(){}

    private static final HungryStaticSingleton hungryStaticSingleton ;

    static {
        hungryStaticSingleton = new HungryStaticSingleton();
    }

    public static HungryStaticSingleton getInstance(){
        return hungryStaticSingleton;
    }
}
