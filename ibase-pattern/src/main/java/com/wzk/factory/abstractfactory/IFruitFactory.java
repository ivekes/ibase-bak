package com.wzk.factory.abstractfactory;

import com.wzk.factory.common.Eat;
import com.wzk.factory.common.Fruit;

public interface IFruitFactory {
    public Fruit getFruit();

    public Eat eat();

}
