package com.project.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// 구독 경로 프리픽스: 클라이언트는 /topic/** 로 구독
		config.enableSimpleBroker("/topic");
		// 발행 경로 프리픽스: 클라이언트는 /app/** 로 전송
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// STOMP 엔드포인트 등록 (SockJS 포함) - CORS 전체 허용
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
	}
}

