package com.wzk.rpc.client;

import com.wzk.rpc.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class TCPTransport {
    public String host;
    public Integer port;

    public TCPTransport(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket(){
        try {
            return new Socket(host,port);
        } catch (IOException e) {
            throw new RuntimeException("连接建立失败");
        }
    }

    public Object send(RpcRequest rpcRequest){
        Socket socket = newSocket();
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException("发起远程调用异常");
        }finally {
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
