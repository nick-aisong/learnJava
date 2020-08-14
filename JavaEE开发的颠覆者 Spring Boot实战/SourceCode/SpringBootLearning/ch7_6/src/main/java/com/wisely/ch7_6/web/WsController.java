package com.wisely.ch7_6.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.wisely.ch7_6.domain.WiselyMessage;
import com.wisely.ch7_6.domain.WiselyResponse;

@Controller
public class WsController {

	@MessageMapping("/welcome") // 1当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址，类似于@RequestMapping
	@SendTo("/topic/getResponse") // 2当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
	public WiselyResponse say(WiselyMessage message) throws Exception {
		Thread.sleep(3000);
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	@Autowired
	private SimpMessagingTemplate messagingTemplate; // 通过SimpMessagingTemplate向浏览器发送消息

	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) { // 在Spring MVC中，可以直接在参数中获得principal，pinciple中包含当前用户的信息
		if (principal.getName().equals("wyf")) { // 这里是一段硬编码，如果发送人是wyf，则发送给wisely；如果发送人是wisely，则发送给wyf，读者可以根据项目实际需要改写此处代码
			messagingTemplate.convertAndSendToUser("wisely", "/queue/notifications",
					principal.getName() + "-send:" + msg); // 通过messagingTemplate.convertAndSendToUser向用户发送消息，第一个参数是接收消息的用户，第二个是浏览器订阅的地址，第三个是消息本身
		} else {
			messagingTemplate.convertAndSendToUser("wyf", "/queue/notifications", principal.getName() + "-send:" + msg);
		}
	}
}