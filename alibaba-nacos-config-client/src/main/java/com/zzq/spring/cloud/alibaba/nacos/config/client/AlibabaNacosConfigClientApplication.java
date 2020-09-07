package com.zzq.spring.cloud.alibaba.nacos.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaNacosConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosConfigClientApplication.class, args);
    }

}
