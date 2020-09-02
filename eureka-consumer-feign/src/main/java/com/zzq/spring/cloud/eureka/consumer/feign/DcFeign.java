package com.zzq.spring.cloud.eureka.consumer.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface DcFeign {
    @GetMapping("/dc")
    public String dc();
}
