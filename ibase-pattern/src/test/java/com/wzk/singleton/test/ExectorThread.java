package com.wzk.singleton.test;

import com.wzk.singleton.threadlocal.ThreadLocalSingleton;

public class ExectorThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
    }
}
