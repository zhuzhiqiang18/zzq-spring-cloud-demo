package com.zzq.spring.cloud.spring.cloud.bus.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    @Value("${zzq.name}")
    String zzqName;
    @GetMapping("test")
    public String test(){
        System.out.println(zzqName);
        return this.zzqName;
    }
}
