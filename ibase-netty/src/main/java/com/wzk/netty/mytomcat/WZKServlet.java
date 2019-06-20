package com.wzk.netty.mytomcat;

/**
 * @author ivekes
 * @date 2019/06/19
 */
public abstract class WZKServlet {

    public void service(WZKRequest request,WZKResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }

    protected abstract void doPost(WZKRequest request, WZKResponse response) throws Exception;

    protected abstract void doGet(WZKRequest request, WZKResponse response) throws Exception;
}
