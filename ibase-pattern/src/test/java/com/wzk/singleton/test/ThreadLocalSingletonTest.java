package com.wzk.singleton.test;

import com.wzk.singleton.threadlocal.ThreadLocalSingleton;
import org.junit.Test;

public class ThreadLocalSingletonTest {

    @Test
    public void getInstanceTest(){

        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());

        t1.start();
        t2.start();

        System.out.println("end");
    }
}
