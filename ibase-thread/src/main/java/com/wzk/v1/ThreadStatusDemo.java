package com.wzk.v1;

import java.util.concurrent.TimeUnit;

/**
 * 线程的几种状态
 */
public class ThreadStatusDemo {
    public static void main(String[] args) {
        //TIMED_WAITING
        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"sleep_thread").start();

        //WAITING
        new Thread(() -> {
            while (true){
                synchronized (ThreadStatusDemo.class){
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"wait_thread").start();

        //TIMED_WAITING
        new Thread(new BlockedDemo(),"block01_thread").start();
        //BLOCKED
        new Thread(new BlockedDemo(),"block02_thread").start();

    }

    static class BlockedDemo extends Thread{

        @Override
        public void run() {
            synchronized (BlockedDemo.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
