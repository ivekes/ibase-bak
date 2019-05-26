package com.wzk.singleton.threadlocal;

/**
 * 注册式单例（伪线程安全）
 */
public class ThreadLocalSingleton {

    private ThreadLocalSingleton(){}

    private static final ThreadLocal<ThreadLocalSingleton>  threadLocalInstance = ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    public static ThreadLocalSingleton getInstance(){
        return threadLocalInstance.get();
    }
}
