package com.project.chat.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRoomRequest(
	@NotBlank String name
) {
}

