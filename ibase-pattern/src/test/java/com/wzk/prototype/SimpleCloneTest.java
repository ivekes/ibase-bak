package com.wzk.prototype;

import com.wzk.prototype.simple.Client;
import com.wzk.prototype.simple.ConcretePrototypeA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleCloneTest {
    @Test
    public void test(){
        // 创建一个具体的需要克隆的对象
        ConcretePrototypeA concretePrototype = new ConcretePrototypeA();
        // 填充属性，方便测试
        concretePrototype.setAge(18);
        concretePrototype.setName("prototype");
        List<String> hobbies = new ArrayList<>();
        concretePrototype.setHobbies(hobbies);
        System.out.println(concretePrototype);

        // 创建Client对象，准备开始克隆
        Client client = new Client(concretePrototype);
        ConcretePrototypeA concretePrototypeClone = (ConcretePrototypeA) client.startClone(concretePrototype);
        System.out.println(concretePrototypeClone);

        System.out.println("克隆对象中的引用类型地址值：" + concretePrototypeClone.getHobbies());
        System.out.println("原对象中的引用类型地址值：" + concretePrototype.getHobbies());
        System.out.println("对象地址比较："+(concretePrototypeClone.getHobbies() == concretePrototype.getHobbies()));
    }
}
