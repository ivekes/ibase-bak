package com.wzk.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio 服务端
 * 处理过程： read --> decode ---> compute ---> encode ---> send
 */
@Slf4j
public class Server {
    private static int server_port = 8888;

    private static ServerSocket serverSocket;

    public static void startServer() throws IOException {
        startServer(server_port);
    }

    private synchronized static void startServer(int server_port) throws IOException{
        if (serverSocket != null){
            return;
        }
        try {
            serverSocket = new ServerSocket(server_port);
            log.info("server 已启动端口：" + server_port);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if (serverSocket != null){
                log.info("server 已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }

    public static void main(String[] args){
        try {
            Server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
