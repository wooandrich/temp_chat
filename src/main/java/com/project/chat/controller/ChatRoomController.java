package com.project.chat.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.chat.model.ChatRoom;
import com.project.chat.service.ChatRoomService;
import jakarta.validation.Valid;
import com.project.chat.controller.dto.CreateRoomRequest;

@RestController
@RequestMapping("/api/rooms")
public class ChatRoomController {

	private final ChatRoomService chatRoomService;

	public ChatRoomController(ChatRoomService chatRoomService) {
		this.chatRoomService = chatRoomService;
	}

	// 채팅방 생성 엔드포인트
	@PostMapping
	public ResponseEntity<ChatRoom> create(@Valid @RequestBody CreateRoomRequest request) {
		return ResponseEntity.ok(chatRoomService.create(request.name()));
	}

	// 채팅방 목록 조회
	@GetMapping
	public ResponseEntity<List<ChatRoom>> list() {
		return ResponseEntity.ok(chatRoomService.list());
	}
}

