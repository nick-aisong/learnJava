package com.wisely.ch9_3_5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	// 使用@RabbitListener来监听RabbitMQ的目的地发送的消息，通过queues属性指定要监听的目的地
	@RabbitListener(queues = "my-queue")
	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}

}
