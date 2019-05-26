package com.wzk.feign.server;

import com.wzk.feign.model.UserInfo;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "app-wzk-server")
@RequestMapping("/user")
public interface UserInfoFeignServer {

    @RequestMapping("/findOne")
    @LoadBalanced
    public UserInfo findOne(@RequestParam("id") Long id);
}
