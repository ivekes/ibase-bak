package com.wzk.controller;

import com.wzk.feign.model.UserInfo;
import com.wzk.feign.server.UserInfoFeignServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserInfoFeignServer userInfoFeignServer;

    @RequestMapping("/findOneOrder")
    public UserInfo findOneOrder(Long id) {
        return restTemplate.getForObject("http://app-wzk-server/user/findOne?id="+id,UserInfo.class);
    }

    @RequestMapping("/findOneOrderFeign")
    public UserInfo findOneOrderFeign(Long id) {
        char a = 'a';
        int b = a;
        return userInfoFeignServer.findOne(id);
    }
    @Bean
    @LoadBalanced //用服务名称访问需添加
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
