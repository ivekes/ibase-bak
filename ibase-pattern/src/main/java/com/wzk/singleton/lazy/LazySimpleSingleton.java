package com.wzk.singleton.lazy;

/**
 * 懒汉线程安全模式，这种模式性能比较低，因加的类锁
 */
public class LazySimpleSingleton {

    private LazySimpleSingleton(){}

    private static LazySimpleSingleton lazySimpleSingleton = null;

    //一般懒汉会造成线程安全问题，即 【lazySimpleSingleton == null】 这块会出现问题，所以加 synchronized
    public static synchronized LazySimpleSingleton getInstance(){
        if (lazySimpleSingleton == null){
            lazySimpleSingleton = new LazySimpleSingleton();
        }
        return lazySimpleSingleton;
    }
}
