package com.wzk.factory.test;

import com.wzk.factory.common.Apple;
import com.wzk.factory.common.Fruit;
import com.wzk.factory.simplefactory.FruitFactory;
import com.wzk.factory.common.Watermelon;
import org.junit.Test;

public class SimpleFactoryTest {
    @Test
    public void test(){
        FruitFactory fruitFactory = new FruitFactory();
        Fruit fruit1 = fruitFactory.getFruit("apple");
        Fruit fruit2 = fruitFactory.getFruit("watermelon");

        System.out.println(fruit1.toString());
        System.out.println(fruit2.toString());
    }
    @Test
    public void test2(){
        FruitFactory fruitFactory = new FruitFactory();
        Fruit fruit1 = fruitFactory.getFruit(Apple.class);
        Fruit fruit2 = fruitFactory.getFruit(Watermelon.class);

        System.out.println(fruit1.toString());
        System.out.println(fruit2.toString());
    }
}
