package com.zzq.spring.cloud.alibaba.nacos.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "alibaba-nacos-server";
    }
}
