package com.wzk.rpc.v2.server;

import com.wzk.rpc.v2.annotation.WZKService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class WZKRpcServer implements ApplicationContextAware,InitializingBean {
    private static final ExecutorService executorService=Executors.newCachedThreadPool();
    private Integer port;
    private Map<String,Object> handlerMap = new HashMap<>();

    public WZKRpcServer(Integer port){
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                executorService.submit(new ProcessorHandler(socket,handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(WZKService.class);
        if (serviceBeanMap != null){
            for (Object serviceBean:serviceBeanMap.values()){
                WZKService wzkService = serviceBean.getClass().getAnnotation(WZKService.class);
                String name = wzkService.value().getName();
                handlerMap.put(name,serviceBean);
            }
        }
    }
}
