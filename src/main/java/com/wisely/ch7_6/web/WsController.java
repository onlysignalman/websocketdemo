package com.wisely.ch7_6.web;

import java.security.Principal;

import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.wisely.ch7_6.domain.WiselyMessage;
import com.wisely.ch7_6.domain.WiselyResponse;

@Controller
public class WsController {

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message) throws Exception{
		Thread.sleep(3000);
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;//通过messagingTemplate向浏览器发送消息
	
	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg){//在springMVC中可直接获取principal，princial中包含用户的信息
		if(principal.getName().equals("wyf")){
			messagingTemplate.convertAndSendToUser("wisely", "/queue/notifications", principal.getName() + "-send:" + msg);
		}else{
			messagingTemplate.convertAndSendToUser("wyf", "/queue/notifications", principal.getName() + "-send:" + msg);
		}
	}

}
