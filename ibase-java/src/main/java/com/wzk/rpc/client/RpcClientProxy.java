package com.wzk.rpc.client;


import java.lang.reflect.Proxy;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class RpcClientProxy {
    public <T> T clientProxy(Class<T> clazz,String host,Integer port){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new RemoteInvocationHandler(host,port));
    }
}
