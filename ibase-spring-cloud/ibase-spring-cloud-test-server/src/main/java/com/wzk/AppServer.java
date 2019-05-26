package com.wzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppServer {

    public static void main(String[] args) {
        SpringApplication.run(AppServer.class,args);
    }

}
