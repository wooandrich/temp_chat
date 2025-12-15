package com.project.chat.model;

import java.time.OffsetDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(indexes = @Index(name = "idx_message_room_created", columnList = "roomId,createdAt"))
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long roomId;

	@Column(nullable = false)
	private String sender;

	@Column(nullable = false, length = 2000)
	private String content;

	@Column(nullable = false)
	private OffsetDateTime createdAt = OffsetDateTime.now();

	protected ChatMessage() {
	}

	public ChatMessage(Long roomId, String sender, String content) {
		this.roomId = roomId;
		this.sender = sender;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public String getSender() {
		return sender;
	}

	public String getContent() {
		return content;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
}

