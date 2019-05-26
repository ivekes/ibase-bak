package com.wzk.factory.abstractfactory;

import com.wzk.factory.common.Apple;
import com.wzk.factory.common.AppleEat;
import com.wzk.factory.common.Eat;
import com.wzk.factory.common.Fruit;

public class AppleFruitFactory implements IFruitFactory {

    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public Eat eat() {
        return new AppleEat();
    }

}
