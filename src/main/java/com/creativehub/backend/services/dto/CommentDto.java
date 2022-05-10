package com.creativehub.backend.services.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CommentDto implements Serializable {
	private final UUID id;
	private final UUID userId;
	private final UUID publicationId;
	private final String message;
}
