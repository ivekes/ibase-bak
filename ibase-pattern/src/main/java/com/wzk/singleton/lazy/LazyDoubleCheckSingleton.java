package com.wzk.singleton.lazy;

/**
 * 懒汉线程安全模式，这种模式性能较高，因加的对象锁
 */
public class LazyDoubleCheckSingleton {

    private LazyDoubleCheckSingleton(){}

    private static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    public static LazyDoubleCheckSingleton getInstance(){
        if (lazyDoubleCheckSingleton == null){
            synchronized (LazyDoubleCheckSingleton.class){
                if (lazyDoubleCheckSingleton == null){
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
