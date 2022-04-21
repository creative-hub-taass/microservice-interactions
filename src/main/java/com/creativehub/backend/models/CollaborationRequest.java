package com.creativehub.backend.models;

import com.creativehub.backend.models.enums.CollaborationRequestCategory;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "collaboration_requests")
public class CollaborationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "sender_id", nullable = false)
	private UUID senderId;

	@Column(name = "receiver_id")
	private UUID receiverId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	@Column(name = "contact")
	private String contact;

	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private CollaborationRequestCategory category;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private CollaborationRequestStatus status;
}