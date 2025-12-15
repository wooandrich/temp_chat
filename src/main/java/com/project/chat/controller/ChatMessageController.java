package com.project.chat.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.chat.model.ChatMessage;
import com.project.chat.service.ChatMessageService;
import jakarta.validation.Valid;
import com.project.chat.controller.dto.SendMessageRequest;

@RestController
@RequestMapping("/api/rooms/{roomId}/messages")
public class ChatMessageController {

	private final ChatMessageService chatMessageService;

	public ChatMessageController(ChatMessageService chatMessageService) {
		this.chatMessageService = chatMessageService;
	}

	// 특정 방으로 REST 방식 메시지 전송
	@PostMapping
	public ResponseEntity<ChatMessage> send(
		@PathVariable Long roomId,
		@Valid @RequestBody SendMessageRequest request
	) {
		return ResponseEntity.ok(chatMessageService.save(roomId, request.sender(), request.content()));
	}

	// 특정 방의 최근 50개 메시지 조회
	@GetMapping
	public ResponseEntity<List<ChatMessage>> latest(@PathVariable Long roomId) {
		return ResponseEntity.ok(chatMessageService.latest(roomId));
	}
}

