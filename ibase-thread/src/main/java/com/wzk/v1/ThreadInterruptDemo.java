package com.wzk.v1;

import java.util.concurrent.TimeUnit;

/**
 * 线程优雅停止
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Worker());
        t.start();
        TimeUnit.SECONDS.sleep(2);
        t.interrupt();

        System.out.println("Main thread stopped.");
    }

    public static class Worker implements Runnable{

        @Override
        public void run() {
            System.out.println("Worker started.");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                Thread curr = Thread.currentThread();
                //再次调用interrupt方法中断自己，将中断状态设置为“中断”
                curr.interrupt();
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Static Call: " + Thread.interrupted());//clear status
                System.out.println("---------After Interrupt Status Cleared----------");
                System.out.println("Static Call: " + Thread.interrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
            }

            System.out.println("Worker stopped.");
        }
    }
}
