package com.wisely.ch9_3_4;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "my-destination") // 1JmsListener是Spring 4.1为我们提供的一个新特性，用来简化JMS开发。我们只需在这个注解的属性destination指定要监听的目的地，即可接收该目的地发送的消息。此例监听my-destination目的地发送的消息
	public void receiveMessage(String message) {
		System.out.println("接受到: <" + message + ">");
	}

}
