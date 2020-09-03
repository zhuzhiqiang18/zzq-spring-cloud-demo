package com.zzq.spring.cloud.eureka.consumer.ribbon.hystrix.controller;

import com.zzq.spring.cloud.eureka.consumer.ribbon.hystrix.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {
    @Autowired
    DcService dcService;

    /**
     * 请求服务 eureka-client
     * @return
     */
    @GetMapping("/consumer")
    public String dc() {
        return dcService.consumer();
    }
}
