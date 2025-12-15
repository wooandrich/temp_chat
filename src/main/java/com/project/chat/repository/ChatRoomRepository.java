package com.project.chat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.chat.model.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	Optional<ChatRoom> findByName(String name);
}

