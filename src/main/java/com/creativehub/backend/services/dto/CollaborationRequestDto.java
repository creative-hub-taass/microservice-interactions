package com.creativehub.backend.services.dto;

import com.creativehub.backend.models.enums.CollaborationRequestCategory;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CollaborationRequestDto implements Serializable {
	private final UUID id;
	private final UUID senderId;
	private final UUID receiverId;
	private final String title;
	private final String description;
	private final Timestamp timestamp;
	private final String contact;
	private final CollaborationRequestCategory category;
	private final CollaborationRequestStatus status;
}
