package com.creativehub.backend.models;

import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
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

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(name = "timestamp", nullable = false, updatable = false)
	@CreationTimestamp
	private Instant timestamp;

	@Column(name = "contact", nullable = false)
	private String contact;

	@Column(name = "category", nullable = false)
	private String category;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private CollaborationRequestStatus status = CollaborationRequestStatus.OPEN;
}