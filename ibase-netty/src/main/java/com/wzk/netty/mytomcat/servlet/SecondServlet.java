package com.wzk.netty.mytomcat.servlet;

import com.wzk.netty.mytomcat.WZKRequest;
import com.wzk.netty.mytomcat.WZKResponse;
import com.wzk.netty.mytomcat.WZKServlet;

/**
 * @author ivekes
 * @date 2019/06/20
 */
public class SecondServlet extends WZKServlet {
    @Override
    protected void doGet(WZKRequest request, WZKResponse response) throws Exception {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(WZKRequest request, WZKResponse response) throws Exception {
        response.write("this is second servlet");
    }
}
