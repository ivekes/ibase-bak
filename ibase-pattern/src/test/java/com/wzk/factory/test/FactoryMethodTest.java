package com.wzk.factory.test;

import com.wzk.factory.common.Fruit;
import com.wzk.factory.factorymethod.AppleFruitFactory;
import com.wzk.factory.factorymethod.IFruitFactory;
import org.junit.Test;

public class FactoryMethodTest{

    @Test
    public void test() {
        IFruitFactory fruitFactory = new AppleFruitFactory();
        Fruit fruit = fruitFactory.getFruit();
        System.out.println(fruit.toString());
    }
}
