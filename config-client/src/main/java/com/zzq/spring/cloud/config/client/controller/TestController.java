package com.zzq.spring.cloud.config.client.controller;

import com.zzq.spring.cloud.config.client.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestConfig testConfig;
    @GetMapping("test")
    public String test(){
        return testConfig.zzqName;
    }
}
