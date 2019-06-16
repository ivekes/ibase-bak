package com.wzk.rpc.my.service;

import com.wzk.rpc.v2.annotation.WZKService;
import com.wzk.rpc.my.api.IHello;

/**
 * @author ivekes
 * @date 2019/06/09
 */
@WZKService(IHello.class)
public class IHelloImpl implements IHello {

    @Override
    public String say(String name) {
        return String.format("%s say hello!",name);
    }
}
