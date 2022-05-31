package com.creativehub.backend.services.dto;

import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
public class CollaborationRequestDto implements Serializable {
	private final UUID id;
	private final UUID senderId;
	private final UUID receiverId;
	private final String title;
	private final String description;
	private final Instant timestamp;
	private final String contact;
	private final String category;
	private final CollaborationRequestStatus status;
}
