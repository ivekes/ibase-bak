package com.wzk.proxy.dynamicproxy.jdkproxy;

import com.wzk.proxy.common.Person;

public class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅");
    }
}
