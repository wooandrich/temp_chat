package com.project.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.project.chat.model.ChatMessage;
import com.project.chat.service.ChatMessageService;
import jakarta.validation.Valid;
import com.project.chat.controller.dto.WebsocketMessage;

@Controller
public class WsChatController {

	private final ChatMessageService chatMessageService;

	public WsChatController(ChatMessageService chatMessageService) {
		this.chatMessageService = chatMessageService;
	}

	// STOMP 메시지 엔드포인트: /app/rooms/{roomId} 로 수신, /topic/rooms/{roomId} 로 브로드캐스트
	@MessageMapping("/rooms/{roomId}")
	@SendTo("/topic/rooms/{roomId}")
	public ChatMessage broadcast(
		@DestinationVariable Long roomId,
		@Valid @Payload WebsocketMessage payload
	) {
		return chatMessageService.save(roomId, payload.sender(), payload.content());
	}
}

