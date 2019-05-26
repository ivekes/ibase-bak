package com.wzk.mysynchronized;

public class MySynchronized{

    private static int i = 0;

    synchronized void increase(){
        System.out.println(i++);
    }

    public static void main(String[] args){
     MySynchronized mySynchronized = new MySynchronized();
     MyThread a = new MyThread(mySynchronized);
     MyThread b = new MyThread(mySynchronized);
     a.start();
     b.start();
     System.out.println("i:"+MySynchronized.i);
    }
}

class MyThread extends Thread{

    private MySynchronized mySynchronized;

    public MyThread(MySynchronized mySynchronized){
        this.mySynchronized = mySynchronized;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            mySynchronized.increase();
        }
    }
}