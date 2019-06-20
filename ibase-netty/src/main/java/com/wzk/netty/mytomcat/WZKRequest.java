package com.wzk.netty.mytomcat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @author ivekes
 * @date 2019/06/20
 */
public class WZKRequest {
    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public WZKRequest(ChannelHandlerContext ctx,HttpRequest request){
        this.ctx = ctx;
        this.request = request;
    }

    public String getUrl(){
        return request.uri();
    }

    public String getMethod(){
        return request.method().name();
    }

    public Map<String,List<String>> getParameters(){
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    public String getParameter(String name){
        Map<String,List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (param == null){
            return null;
        }else{
            return param.get(0);
        }
    }
}
