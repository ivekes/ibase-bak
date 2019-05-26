package com.wzk.myvolatile;

import java.util.concurrent.TimeUnit;

public class MyVolatileDemo {
    private static volatile int[] a = new int[3];

    public static void main(String[] args){
     new Thread(()->{
         for (int i=0;i<10;i++){
             try {
                 System.out.println(Thread.currentThread().getName() + ",before:" + a[1] );
                 TimeUnit.SECONDS.sleep(2);
                 a[1]=  i;
                 System.out.println(Thread.currentThread().getName() + ",after:" + a[1]);
                 System.out.println("--------------------------------------1"+i);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

     }).start();

     new Thread(()->{
         for (int i=0;i<10;i++){
             try {
                 if(a[i]==2){
                     break;
                 }
                 System.out.println(Thread.currentThread().getName() + ",before:" +a[1]);
                 TimeUnit.SECONDS.sleep(2);
                 a[1]= 100+i;
                 System.out.println(Thread.currentThread().getName() + ",after:" + a[1]);
                 System.out.println("--------------------------------------2"+i);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
     }).start();
    }
}

class A{
    private String a;

    public A(String a){
        this.a = a;
    }
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }
}