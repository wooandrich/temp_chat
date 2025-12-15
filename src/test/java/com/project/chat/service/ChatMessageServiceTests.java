package com.project.chat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import com.project.chat.model.ChatMessage;
import com.project.chat.model.ChatRoom;

@SpringBootTest
@Transactional
class ChatMessageServiceTests {

	@Autowired
	private ChatMessageService chatMessageService;

	@Autowired
	private ChatRoomService chatRoomService;

	@MockBean
	private SimpMessagingTemplate messagingTemplate;

	@Test
	@DisplayName("메시지를 저장하고 최신 50개를 시간순으로 조회")
	void saveAndFetchLatestMessages() {
		// given: 채팅방과 메시지 내용 준비
		ChatRoom room = chatRoomService.create("dev");
		Long roomId = room.getId();

		// when: 메시지 2개 전송
		ChatMessage first = chatMessageService.save(roomId, "alice", "hello");
		ChatMessage second = chatMessageService.save(roomId, "bob", "hi");

		// then: 전송한 순서대로 반환되고 브로드캐스트 호출됨
		List<ChatMessage> latest = chatMessageService.latest(roomId);
		assertThat(latest).extracting(ChatMessage::getId).containsExactly(first.getId(), second.getId());

		ArgumentCaptor<String> destinationCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<ChatMessage> payloadCaptor = ArgumentCaptor.forClass(ChatMessage.class);
		verify(messagingTemplate, times(2)).convertAndSend(destinationCaptor.capture(), payloadCaptor.capture());
		assertThat(destinationCaptor.getAllValues()).allMatch(dest -> dest.equals("/topic/rooms/" + roomId));
		assertThat(payloadCaptor.getAllValues())
			.extracting(ChatMessage::getContent)
			.containsExactly("hello", "hi");
	}
}

