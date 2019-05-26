package com.wzk.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class Client {
    private static int server_port = 8888;
    private static String server_ip = "127.0.0.1";


    public static void send(String message) throws IOException {
        send(server_port,message);
    }

    private static void send(int server_port,String message) throws IOException{
        log.info("send message:"+message);
        Socket socket = null;
        try {
            socket = new Socket(server_ip,server_port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(message);
            log.info("result:"+in.readLine());
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (socket != null){
                log.info("server 已关闭");
                socket.close();
                socket = null;
            }
        }
    }
    public static void main(String[] args){
        try {
            Client.send("123洒洒地说道");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
