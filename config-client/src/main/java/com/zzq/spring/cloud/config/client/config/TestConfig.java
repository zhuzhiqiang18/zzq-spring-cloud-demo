package com.zzq.spring.cloud.config.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class TestConfig {
    @Value("${zzq.name}")
    public String zzqName;
}
