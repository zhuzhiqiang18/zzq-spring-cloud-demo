package com.zzq.spring.cloud.eureka.consumer.controller;

import com.zzq.spring.cloud.eureka.consumer.feign.DcFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class DcController {
    @Autowired
    DcFeign dcFeign;

    /**
     * 请求服务 eureka-client
     * @return
     */
    @GetMapping("/consumer")
    public String dc() {
        return dcFeign.dc();
    }
}
