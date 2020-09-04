package com.zzq.spring.cloud.stream;

import com.zzq.spring.cloud.stream.topic.TopicOutPut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableBinding(value = {TopicOutPut.class})
public class ZzqSendTest {

	@Autowired
	private TopicOutPut topicOutPut;

	@Test
	public void sinkSenderTester() {
		for (int i=0;i<100;i++)
			topicOutPut.output().send(MessageBuilder.withPayload("Hi "+i).build());
	}

}
