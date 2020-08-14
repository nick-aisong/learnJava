package com.wisely.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker // 1通过@EnableWebSocketMessageBroker注解开启使用STOMP协议来传输基于代理（message broker）的消息，这时控制器支持使用@MessageMapping，就像使用@RequestMapping一样
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) { // 2注册STOMP协议的节点（endpoint），并映射的指定的URL
		registry.addEndpoint("/endpointWisely").withSockJS(); // 3注册一个STOMP的endpoint，并指定使用SockJS协议
		registry.addEndpoint("/endpointChat").withSockJS(); // 注册一个名为/endpointChat的endpoint
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) { // 4配置消息代理（Message Broker）
		//registry.enableSimpleBroker("/topic"); // 5广播式应配置一个/topic消息代理
		registry.enableSimpleBroker("/queue", "/topic"); // 点对点式应增加一个/queue消息代理
	}

}
