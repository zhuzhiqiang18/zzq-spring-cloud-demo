package com.zzq.spring.cloud.alibaba.nacos.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigTestController {
    @Value("${zzq.name:}")
    public String zzqName;

    @GetMapping("/test")
    public String test(){
        return zzqName;
    }
}
