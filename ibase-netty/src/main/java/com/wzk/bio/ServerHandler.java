package com.wzk.bio;


import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression ,result;
            while(true){
                if ((expression = in.readLine()) == null){
                    break;
                }
                log.info("server端接收到消息："+expression);
                result = expression;
                out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                    in = null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                if (out != null){
                    out.close();
                    out = null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (socket != null){
                    socket.close();
                    socket = null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
