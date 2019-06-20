package com.wzk.netty.mytomcat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ivekes
 * @date 2019/06/16
 */
public class WZKTomcat {

    public static void main(String[] args) {
        new WZKTomcat().start();
    }

    private int port = 8080;
    private Map<String, WZKServlet> servletMap = new HashMap<>();
    Properties webxml = new Properties();

    //加载web.xml,初始化 servletmapping 对象
    private void init() {

        String path = this.getClass().getResource("/").getPath();
        try {
            FileInputStream fileInputStream = new FileInputStream(path + "web.properties");
            webxml.load(fileInputStream);
            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    WZKServlet servlet = (WZKServlet) Class.forName(className).newInstance();
                    servletMap.put(url, servlet);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //启动容器，
    public void start() {
        init();
        //Netty封装了NIO，Reactor模型，Boss，worker
        // Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // Netty服务
        //ServetBootstrap   ServerSocketChannel
        ServerBootstrap server = new ServerBootstrap();
        // 链路式编程
        server.group(bossGroup, workerGroup)
                // 主线程处理类,看到这样的写法，底层就是用反射
                .channel(NioServerSocketChannel.class)
                // 子线程处理类 , Handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    // 客户端初始化处理
                    protected void initChannel(SocketChannel client) throws Exception {
                        // 无锁化串行编程
                        //Netty对HTTP协议的封装，顺序有要求
                        // HttpResponseEncoder 编码器
                        client.pipeline().addLast(new HttpResponseEncoder());
                        // HttpRequestDecoder 解码器
                        client.pipeline().addLast(new HttpRequestDecoder());
                        // 业务逻辑处理
                        client.pipeline().addLast(new WZKTomcatHandler());
                    }

                })
                // 针对主线程的配置 分配线程最大数量 128
                .option(ChannelOption.SO_BACKLOG, 128)
                // 针对子线程的配置 保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            // 启动服务器
            ChannelFuture f = server.bind(port).sync();
            System.out.println("GP Tomcat 已启动，监听的端口是：" + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public class WZKTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest) {
                HttpRequest req = (HttpRequest) msg;

                // 转交给我们自己的request实现
                WZKRequest request = new WZKRequest(ctx, req);
                // 转交给我们自己的response实现
                WZKResponse response = new WZKResponse(ctx, req);
                // 实际业务处理
                String url = request.getUrl();

                if (servletMap.containsKey(url)) {
                    servletMap.get(url).service(request, response);
                } else {
                    response.write("404 - Not Found");
                }

            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        }

    }
}
