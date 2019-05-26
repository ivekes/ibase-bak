package com.wzk.proxy;

import com.wzk.proxy.staticproxy.Father;
import com.wzk.proxy.staticproxy.Son;
import org.junit.Test;

public class StaticProxyTest {

    @Test
    public void test(){
        Father father = new Father(new Son());
        father.findLove();
    }
}
