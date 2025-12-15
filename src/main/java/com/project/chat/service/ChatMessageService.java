package com.project.chat.service;

import java.util.Collections;
import java.util.List;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.chat.model.ChatMessage;
import com.project.chat.repository.ChatMessageRepository;

@Service
@Transactional
public class ChatMessageService {

	private final ChatMessageRepository messageRepository;
	private final SimpMessagingTemplate messagingTemplate;

	public ChatMessageService(ChatMessageRepository messageRepository, SimpMessagingTemplate messagingTemplate) {
		this.messageRepository = messageRepository;
		this.messagingTemplate = messagingTemplate;
	}

	// 메시지 저장 후 해당 방 구독자에게 즉시 브로드캐스트
	public ChatMessage save(Long roomId, String sender, String content) {
		ChatMessage saved = messageRepository.save(new ChatMessage(roomId, sender, content));
		// broadcast to subscribers of the room
		messagingTemplate.convertAndSend("/topic/rooms/" + roomId, saved);
		return saved;
	}

	@Transactional(readOnly = true)
	public List<ChatMessage> latest(Long roomId) {
		List<ChatMessage> messages = messageRepository.findTop50ByRoomIdOrderByCreatedAtDesc(roomId);
		Collections.reverse(messages); // oldest first
		return messages;
	}
}

