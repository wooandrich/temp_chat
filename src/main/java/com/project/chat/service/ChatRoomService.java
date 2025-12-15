package com.project.chat.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.chat.model.ChatRoom;
import com.project.chat.repository.ChatRoomRepository;

@Service
@Transactional
public class ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	public ChatRoomService(ChatRoomRepository chatRoomRepository) {
		this.chatRoomRepository = chatRoomRepository;
	}

	// 채팅방 생성 로직
	public ChatRoom create(String name) {
		return chatRoomRepository.save(new ChatRoom(name));
	}

	@Transactional(readOnly = true)
	public List<ChatRoom> list() {
		return chatRoomRepository.findAll();
	}
}

