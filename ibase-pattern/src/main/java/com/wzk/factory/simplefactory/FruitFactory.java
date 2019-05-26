package com.wzk.factory.simplefactory;

import com.wzk.factory.common.Apple;
import com.wzk.factory.common.Fruit;
import com.wzk.factory.common.Watermelon;

public class FruitFactory {
    public Fruit getFruit(String name){
        if("apple".equals(name)){
            return new Apple();
        }else if("watermelon".equals(name)){
            return new Watermelon();
        }else {
            return null;
        }
    }

    public Fruit getFruit(Class clazz){
        if(clazz != null){
            try {
                return (Fruit)clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
