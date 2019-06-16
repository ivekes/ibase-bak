package com.wzk.rpc.v2.conf;

import com.wzk.rpc.v2.client.RpcClientProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ivekes
 * @date 2019/06/09
 */
@Configuration
public class WZKClientConf {

    @Bean(name = "rpcClientProxy")
    public RpcClientProxy getRpcClientProxy(){
        return new RpcClientProxy();
    }
}
