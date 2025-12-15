package com.project.chat.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.project.chat.model.ChatRoom;

@SpringBootTest
@Transactional
class ChatRoomServiceTests {

	@Autowired
	private ChatRoomService chatRoomService;

	@Test
	@DisplayName("채팅방을 생성하고 목록에 포함되는지 확인")
	void createRoomAndList() {
		// given: 채팅방 이름 준비
		String roomName = "general";

		// when: 채팅방 생성
		ChatRoom created = chatRoomService.create(roomName);

		// then: 생성된 방이 목록에 존재
		List<ChatRoom> rooms = chatRoomService.list();
		assertThat(created.getId()).isNotNull();
		assertThat(rooms)
			.extracting(ChatRoom::getName)
			.contains(roomName);
	}
}

