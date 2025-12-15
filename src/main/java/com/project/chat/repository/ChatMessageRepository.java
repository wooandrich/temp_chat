package com.project.chat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.chat.model.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	List<ChatMessage> findTop50ByRoomIdOrderByCreatedAtDesc(Long roomId);
}

