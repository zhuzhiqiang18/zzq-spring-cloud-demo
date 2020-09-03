package com.zzq.spring.cloud.stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableBinding(value = {ZzqSendTest.TopicSend.class})
public class ZzqSendTest {

	@Autowired
	private TopicSend topicSend;

	@Test
	public void sinkSenderTester() {
		topicSend.output().send(MessageBuilder.withPayload("produce a message ：http://blog.didispace.com").build());
	}

	public interface TopicSend{
		String topicName = "zzq";

		@Output("zzq")
		MessageChannel output();
	}

}
