package com.wzk.singleton.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrentExecutor {

    /**
     *
     * @param runHandler        业务逻辑
     * @param executeCount      发起请求总数
     * @param concurrentCount   同时并发执行的线程数
     * @throws Exception
     */
    public static void execute(final RunHandler runHandler,int executeCount,int concurrentCount) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(concurrentCount);//控制信号量，此处用于控制并发的线程数
        final CountDownLatch countDownLatch = new CountDownLatch(executeCount);//闭锁，实现计数量递减
        for (int i=0; i<executeCount;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//获取许可
                        runHandler.handler();
                        semaphore.release();//释放许可
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        executorService.shutdown();
    }

    public interface RunHandler{
        void handler();
    }
}
