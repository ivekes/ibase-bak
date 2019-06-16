package com.wzk.rpc.v2.conf;

import com.wzk.rpc.v2.server.WZKRpcServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ivekes
 * @date 2019/06/09
 */
@Configuration
@ComponentScan(basePackages="com.wzk.rpc.my")
public class WZKServerConf {

    @Bean(name = "wzkRpcServer")
    public WZKRpcServer getMyRpcServer(){
        return new WZKRpcServer(8080);
    }
}
