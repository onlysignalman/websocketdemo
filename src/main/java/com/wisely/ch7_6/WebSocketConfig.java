package com.wisely.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker//注解开启STOMP协议基于代理(message broker)的消息
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpointWisely").withSockJS();
		registry.addEndpoint("/endpointChat").withSockJS();//注册一个名为endpointChart的endpoint
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//registry.enableSimpleBroker("/topic");
		registry.enableSimpleBroker("/queue","/topic");//点对点式增加一个/queue的消息代理
	}

}
