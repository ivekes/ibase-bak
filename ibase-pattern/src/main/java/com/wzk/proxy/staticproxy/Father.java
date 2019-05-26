package com.wzk.proxy.staticproxy;

import com.wzk.proxy.common.Person;

public class Father implements Person {

    private Son person;

    public Father(Son son){
        this.person = son;
    }

    @Override
    public void findLove() {
        System.out.println("father find");
        this.person.findLove();
        System.out.println("父母同意，办事");
    }
}
