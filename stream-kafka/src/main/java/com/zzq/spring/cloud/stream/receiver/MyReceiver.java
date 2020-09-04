package com.zzq.spring.cloud.stream.receiver;

import com.zzq.spring.cloud.stream.topic.TopicInPut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


//触发绑定@EnableBinding 消息通道
@EnableBinding(TopicInPut.class)
public class MyReceiver {
    private static Logger logger = LoggerFactory.getLogger(MyReceiver.class);

    public int i=0;


    @StreamListener(TopicInPut.ZZQ)
    public void receive(Object payload) {

        logger.info("MyReceiver Received: " + payload);
        i++;
        /*if(i>=3){
            System.out.println(i);
        }else {
            //模拟报错重试
            throw new RuntimeException("模拟报错消息重试"+i);
        }*/

    }

}
