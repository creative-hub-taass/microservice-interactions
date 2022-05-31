package com.creativehub.backend.services;

import com.creativehub.backend.services.dto.CommentDto;
import com.creativehub.backend.services.dto.LikeDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface InteractionsManager {
	// LIKES
	LikeDto like(LikeDto like);

	void deleteLikeBy(UUID userId, UUID publicationId);

	void deleteLikeById(UUID likeId);

	List<LikeDto> likesOfUser(UUID userId);

	List<LikeDto> likesOfPublication(UUID userId);

	// COMMENTS
	CommentDto comment(CommentDto comment);

	void deleteCommentById(UUID commentId);

	List<CommentDto> commentsOfPublication(UUID publicationId);

	boolean likeExists(UUID userId, UUID publicationId);

	boolean commentExists(UUID userId, UUID publicationId);

	Map<UUID, Integer> likesCountsByPublications(List<UUID> idsList);

	Map<UUID, List<CommentDto>> commentsByPublications(List<UUID> idsList);

	void saveLikes(List<LikeDto> likes);

	void saveComments(List<CommentDto> comments);
}
