package com.wzk.rpc.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class RpcServer {

    private static final ExecutorService executorService=Executors.newCachedThreadPool();

    public void publisher(Object service,Integer port){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                executorService.submit(new ProcessorHandler(socket,service));
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
}
