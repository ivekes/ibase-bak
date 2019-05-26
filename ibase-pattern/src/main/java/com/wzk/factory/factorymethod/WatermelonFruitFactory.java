package com.wzk.factory.factorymethod;

import com.wzk.factory.common.Fruit;
import com.wzk.factory.common.Watermelon;

public class WatermelonFruitFactory implements IFruitFactory {
    @Override
    public Fruit getFruit() {
        return new Watermelon();
    }
}
