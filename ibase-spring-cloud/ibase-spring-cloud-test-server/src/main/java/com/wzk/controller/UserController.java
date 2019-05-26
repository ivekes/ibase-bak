package com.wzk.controller;

import com.wzk.model.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/findOne")
    public UserInfo findOne(Long id){
        return new UserInfo(id,"name"+id+"|"+serverPort);
    }
}
