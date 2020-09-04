package com.zzq.spring.cloud.stream.topic;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TopicOutPut {
    String ZZQ="zzq";

    @Output(ZZQ)
    MessageChannel output();
}
