package com.wzk.factory.factorymethod;

import com.wzk.factory.common.Apple;
import com.wzk.factory.common.Fruit;

public class AppleFruitFactory implements IFruitFactory {

    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
