package com.wzk.rpc;

import org.junit.Test;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class RpcTest {

    @Test
    public void server(){
        /*IHello iHello = new IHelloImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(iHello,8080);*/
    }

    @Test
    public void client(){
        /*RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IHello iHello =  rpcClientProxy.clientProxy(IHello.class,"localhost",8080);
        System.out.println(iHello.say("zhangsan"));*/
    }
}
