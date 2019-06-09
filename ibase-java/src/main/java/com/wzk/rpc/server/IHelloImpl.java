package com.wzk.rpc.server;

import com.wzk.rpc.api.IHello;

/**
 * @author ivekes
 * @date 2019/06/09
 */
public class IHelloImpl implements IHello {

    @Override
    public String say(String name) {
        return String.format("%s say hello!",name);
    }
}
