package com.project.chat.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record WebsocketMessage(
	@NotBlank String sender,
	@NotBlank String content
) {
}

