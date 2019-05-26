package com.wzk.factory.test;

import com.wzk.factory.common.Fruit;
import com.wzk.factory.abstractfactory.AppleFruitFactory;
import com.wzk.factory.common.Eat;
import com.wzk.factory.abstractfactory.IFruitFactory;
import org.junit.Test;

public class AbstractFactoryTest {
    @Test
    public void test(){
        IFruitFactory fruitFactory = new AppleFruitFactory();
        Fruit fruit = fruitFactory.getFruit();
        Eat eat = fruitFactory.eat();
        System.out.println(fruit.toString());
        System.out.println(eat.eat());
    }
}
