package com.zzq.spring.cloud.spring.cloud.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudBusApplication.class, args);
    }

}
