package com.zzq.spring.cloud.stream.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TopicInPut {
    String ZZQ="zzq";

    @Input(ZZQ)
    SubscribableChannel zzqInput();
}
