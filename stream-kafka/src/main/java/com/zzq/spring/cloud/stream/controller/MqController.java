package com.zzq.spring.cloud.stream.controller;

import com.zzq.spring.cloud.stream.topic.TopicOutPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(TopicOutPut.class)
public class MqController {
    @Autowired
    TopicOutPut topicOutPut;

    @GetMapping("send")
    public String send(){
        topicOutPut.output().send(MessageBuilder.withPayload(System.currentTimeMillis()).build());
        return "ok";
    }

}
