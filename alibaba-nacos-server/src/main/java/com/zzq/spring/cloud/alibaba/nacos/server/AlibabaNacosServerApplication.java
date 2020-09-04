package com.zzq.spring.cloud.alibaba.nacos.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaNacosServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosServerApplication.class, args);
    }

}
