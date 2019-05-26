package com.wzk.singleton.lazy;

/**
 * 这种形式避免了饿汉式的内存浪费，又兼顾了懒汉 synchronized 性能问题,性能最高
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){
        if (LazyHolder.LAZY_INNER_CLASS_SINGLETON == null){//防止通过反射创建多个实例
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    public static final LazyInnerClassSingleton getInstance(){
        return LazyHolder.LAZY_INNER_CLASS_SINGLETON;
    }

    private static class LazyHolder{
        private static final LazyInnerClassSingleton LAZY_INNER_CLASS_SINGLETON = new LazyInnerClassSingleton();
    }

}
