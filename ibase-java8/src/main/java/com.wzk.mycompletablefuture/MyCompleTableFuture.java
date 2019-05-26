package com.wzk.mycompletablefuture;


import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

/**
 * 此类为 CompleTableFuture 用法示例
 *
 * 第一阶段，创建异步操作：
 * CompletableFuture 提供了四个静态方法来创建一个异步操作。
 * @link CompletableFuture#runAsync(Runnable runnable)
 * @link CompletableFuture#runAsync(Runnable runnable, Executor executor)
 * @link CompletableFuture#supplyAsync(Supplier<U> supplier)
 * @link CompletableFuture#supplyAsync(Supplier<U> supplier, Executor executor)
 * 没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。如果指定线程池，则使用指定的线程池运行
 * runAsync方法不支持返回值,supplyAsync可以支持返回值
 *
 * 第二阶段，计算结果完成时的回调方法：
 * 当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action。主要是下面的方法
 * @link CompletableFuture#whenComplete(BiConsumer<? super T,? super Throwable> action)
 * @link CompletableFuture#whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 * @link CompletableFuture#whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 * @link CompletableFuture#exceptionally(Function<Throwable,? extends T> fn)
 * 可以看到Action的类型是BiConsumer<? super T,? super Throwable>它可以处理正常的计算结果，或者异常情况
 * whenComplete 和 whenCompleteAsync 的区别：
 * whenComplete：是执行当前任务的线程,继续执行 whenComplete 的任务。
 * whenCompleteAsync：是执行当前任务的线程,继续执行 whenCompleteAsync 的任务提交给线程池来进行执行。
 *
 * 第三阶段，其他操作
 *
 * thenApply 第二个任务依赖第一个任务的结果
 * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
 * @link CompletableFuture#thenApply(Function<? super T,? extends U> fn)
 * @link CompletableFuture#thenApplyAsync(Function<? super T,? extends U> fn)
 * @link CompletableFuture#thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 * Function<? super T,? extends U> T：上一个任务返回结果的类型 U：当前任务的返回值类型
 *
 * handle 是执行任务完成时对结果的处理
 * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法
 * @link CompletableFuture#handle(BiFunction<? super T, Throwable, ? extends U> fn)
 * @link CompletableFuture#handleAsync(BiFunction<? super T, Throwable, ? extends U> fn)
 * @link CompletableFuture#handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor)
 * BiFunction<? super T, Throwable, ? extends U> T：上一个任务返回结果的类型 Throwable: 上一个任务的异常信息 U：当前任务的返回值类型
 *
 * thenAccept 接收任务的处理结果，并消费处理，无返回结果
 * @link CompletableFuture#thenAccept(Consumer<? super T> action)
 * @link CompletableFuture#thenAcceptAsync(Consumer<? super T> action)
 * @link CompletableFuture#thenAcceptAsync(Consumer<? super T> action,Executor executor)
 *
 * thenRun
 * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenAccept
 * @link CompletableFuture#thenRun(Runnable action)
 * @link CompletableFuture#thenRunAsync(Runnable action)
 * @link CompletableFuture#thenRunAsync(Runnable action,Executor executor)
 *
 * thenCombine
 * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理
 * @link CompletableFuture#thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn)
 * @link CompletableFuture#thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn)
 * @link CompletableFuture#thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor)
 *
 * thenAcceptBoth
 * 当两个CompletionStage都执行完成后，把结果一块交给 thenAcceptBoth 来进行消耗
 * @link CompletableFuture#thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action)
 * @link CompletableFuture#thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action)
 * @link CompletableFuture#thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action,     Executor executor)
 *
 * applyToEither
 * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作
 * @link CompletableFuture#applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn)
 * @link CompletableFuture#applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn)
 * @link CompletableFuture#applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor)
 *
 * acceptEither
 * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作
 * @link CompletableFuture#acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action)
 * @link CompletableFuture#acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action)
 * @link CompletableFuture#acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action,Executor executor)
 *
 * runAfterEither
 * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
 * @link CompletableFuture#runAfterEither(CompletionStage<?> other,Runnable action)
 * @link CompletableFuture#runAfterEitherAsync(CompletionStage<?> other,Runnable action)
 * @link CompletableFuture#runAfterEitherAsync(CompletionStage<?> other,Runnable action,Executor executor)
 *
 * runAfterBoth
 * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
 * 返回值：CompletionStage<Void>
 * @link CompletableFuture#runAfterBoth(CompletionStage<?> other,Runnable action)
 * @link CompletableFuture#runAfterBothAsync(CompletionStage<?> other,Runnable action)
 * @link CompletableFuture#runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor)
 *
 * thenCompose
 * 对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
 * 返回值：CompletableFuture<U>
 * @link CompletableFuture#thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)
 * @link CompletableFuture#thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn)
 * @link CompletableFuture#thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor)
 *
 */
public class MyCompleTableFuture {

    /**
     * runAsync 不支持返回值
     */
    @Test
    public void runAsync() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });
        System.out.println(future.get() == null);
    }

    /**
     * supplyAsync 支持返回值
     */
    @Test
    public void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = "+time);
    }

    /**
     * whenComplete
     */
    @Test
    public void whenComplete() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        CompletableFuture<Void> voidCompletableFuture = future.whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！" );
            }

        });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("执行失败！"+t.getMessage());
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * whenCompleteAsync
     * @throws Exception
     */
    @Test
    public void whenCompleteAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if(new Random().nextInt()%2>=0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        future.whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("执行完成！" );
            }

        });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("执行失败！"+t.getMessage() );
                return null;
            }
        });
        //whenCompleteAsync 有可能在任务没有完成时就结束
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * thenApply
     * @throws Exception
     */
    @Test
    public void thenApply() throws Exception{
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t*5;
                System.out.println("result2="+result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }

    /**
     * handle
     * @throws Exception
     */
    @Test
    public void handle() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                int i= 10/(new Random().nextInt(10)/2);
                return i;
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                int result = -1;
                if(throwable==null){
                    result = param * 2;
                }else{
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }

    /**
     * thenAccept
     * @throws Exception
     */
    @Test
    public void thenAccept() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {return new Random().nextInt(10);}).thenAccept(integer -> {
            System.out.println(integer);
        });
        future.get();
    }

    /**
     * thenRun
     * @throws Exception
     */
    @Test
    public void thenRun() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(() -> {
            System.out.println("thenRun ...");
        });
        future.get();
    }

    /**
     * thenCombine
     * @throws Exception
     */
    @Test
    public void thenCombine() throws Exception{
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "world";
            }
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String t, String u) {
                return t+" "+u;
            }
        });
        System.out.println(result.get());
    }

    /**
     * thenAcceptBoth
     * @throws Exception
     */
    @Test
    public void thenAcceptBoth() throws Exception{
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2="+t);
                return t;
            }
        });
        CompletableFuture<Void> f3 = f1.thenAcceptBoth(f2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer t, Integer u) {
                System.out.println("f1=" + t + ";f2=" + u + ";");
            }
        });
        f3.get();
    }

    /**
     * applyToEither
     * @throws Exception
     */
    @Test
    public void applyToEither() throws Exception{
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2="+t);
                return t;
            }
        });

        CompletableFuture<Integer> result = f1.applyToEither(f2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                System.out.println("applyToEither=" + t);
                return t * 2;
            }
        });

        System.out.println("main get =" + result.get());
    }

    /**
     * acceptEither
     * @throws Exception
     */
    @Test
    public void acceptEither() throws Exception{
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2="+t);
                return t;
            }
        });
        f1.acceptEither(f2, new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
        });
    }

    /**
     * runAfterEither
     * @throws Exception
     */
    @Test
    public void runAfterEither() throws Exception{
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2="+t);
                return t;
            }
        });
        f1.runAfterEither(f2, new Runnable() {
            @Override
            public void run() {
                System.out.println("上面有一个已经完成了。");
            }
        });
    }

    /**
     * runAfterBoth
     * @throws Exception
     */
    @Test
    public void runAfterBoth() throws Exception{
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1="+t);
                return t;
            }
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2="+t);
                return t;
            }
        });
        f1.runAfterBoth(f2, new Runnable() {

            @Override
            public void run() {
                System.out.println("上面两个任务都执行完成了。");
            }
        });
    }

    /**
     * thenCompose
     * @throws Exception
     */
    @Test
    public void thenCompose() throws Exception{
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                int t = new Random().nextInt(3);
                System.out.println("t1="+t);
                return String.valueOf(t);
            }
        }).thenCompose(new Function<String, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(String param) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int t = Integer.valueOf(param) *2;
                        System.out.println("t2="+t);
                        return t;
                    }
                });
            }

        });
        System.out.println("thenCompose result : "+f.get());
    }
}
