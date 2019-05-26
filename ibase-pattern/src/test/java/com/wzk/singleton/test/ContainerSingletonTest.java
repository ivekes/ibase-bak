package com.wzk.singleton.test;

import com.wzk.singleton.register.ContainerSingleton;
import org.junit.Test;

public class ContainerSingletonTest {
    @Test
    public void getInstanceTest(){
        try {
            long start = System.currentTimeMillis();
            ConcurrentExecutor.execute(new ConcurrentExecutor.RunHandler() {
                @Override
                public void handler() {
                    Object obj = ContainerSingleton.getInstance("com.wzk.singleton.test.Pojo");
                    System.out.println(System.currentTimeMillis() + ": " + obj);
                }
            },10,6);
            long end = System.currentTimeMillis();
            System.out.println("总耗时：" + (end - start) + " ms.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
