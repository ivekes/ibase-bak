package com.wzk.rpc;

import com.wzk.rpc.my.api.IHello;
import com.wzk.rpc.my.service.IHelloImpl;
import com.wzk.rpc.v2.client.RpcClientProxy;
import com.wzk.rpc.v2.conf.WZKClientConf;
import com.wzk.rpc.v2.conf.WZKServerConf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class RpcV2Test {

    @Test
    public void server(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WZKServerConf.class);
        ((AnnotationConfigApplicationContext) applicationContext).start();
    }

    @Test
    public void client(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WZKClientConf.class);
        RpcClientProxy rpcClientProxy = applicationContext.getBean(RpcClientProxy.class);
        IHello iHello =  rpcClientProxy.clientProxy(IHello.class,"localhost",8080);
        System.out.println(iHello.say("zhangsan"));
    }
}
