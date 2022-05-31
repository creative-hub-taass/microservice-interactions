package com.creativehub.backend.models;

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
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "user_id", nullable = false)
	private UUID userId;

	@Column(name = "pubblication_id", nullable = false)
	private UUID publicationId;

	@Column(name = "timestamp", nullable = false, updatable = false)
	@CreationTimestamp
	private Instant timestamp;

	@Column(name = "message", nullable = false, columnDefinition = "TEXT")
	private String message;
}