package com.wzk.rpc.v2.client;

import com.wzk.rpc.v2.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private Integer port;

    public RemoteInvocationHandler(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        TCPTransport tcpTransport = new TCPTransport(host,port);
        return tcpTransport.send(rpcRequest);
    }
}
