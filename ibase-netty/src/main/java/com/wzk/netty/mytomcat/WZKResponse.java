package com.wzk.netty.mytomcat;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.util.List;
import java.util.Map;

/**
 * @author ivekes
 * @date 2019/06/20
 */
public class WZKResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public WZKResponse(ChannelHandlerContext ctx,HttpRequest request){
        this.ctx = ctx;
        this.request = request;
    }

    public void write(String out)throws Exception{
        try {
            if (out == null || out.length() == 0){
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("utf-8")));
            response.headers().set("Content-Type","text/html");
            ctx.write(response);
        }finally {
            ctx.flush();
            ctx.close();
        }
    }

}
