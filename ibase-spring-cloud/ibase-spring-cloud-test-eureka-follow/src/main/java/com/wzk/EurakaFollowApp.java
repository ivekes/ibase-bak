package com.wzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurakaFollowApp {
    public static void main(String[] args) {
        SpringApplication.run(EurakaFollowApp.class,args);
    }
}
