package com.zzq.spring.cloud.stream.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;


//触发绑定@EnableBinding 消息通道
@EnableBinding(MyReceiver.Topic.class)
public class MyReceiver {
    private static Logger logger = LoggerFactory.getLogger(MyReceiver.class);

    @StreamListener(Topic.topicName)
    public void receive(Object payload) {
        logger.info("MyReceiver Received: " + payload);
    }

    /**
     * 自定义绑定
     */
    public interface Topic{
        String topicName = "zzq";

        @Input("zzq")
        SubscribableChannel input();
    }
}
