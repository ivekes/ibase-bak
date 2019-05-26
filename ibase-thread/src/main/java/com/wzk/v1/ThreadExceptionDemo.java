package com.wzk.v1;

import java.util.concurrent.TimeUnit;

/**
 * 阻塞异常
 */
public class ThreadExceptionDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("1");
                } catch (InterruptedException e) {//中断一个处于阻塞状态的线程，会报此异常，此异常表示已经收到命令，但不会中断线程，需手动处理：join，wait，queue.take()
                    e.printStackTrace();
                    System.out.println("5");
                    Thread.currentThread().interrupt();//或者用 break 停止 while
                    System.out.println("6");
                }
            }
            System.out.println("2");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println("3");
        System.out.println(thread.isInterrupted());
        System.out.println("4");
    }
}
