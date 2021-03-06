package com.creativehub.backend.controllers;

import com.creativehub.backend.services.InteractionsManager;
import com.creativehub.backend.services.dto.CommentDto;
import com.creativehub.backend.services.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/interactions")
@RequiredArgsConstructor
public class InteractionsController {
	private final InteractionsManager interactionsManager;

	@PostMapping("/comment")
	public ResponseEntity<CommentDto> comment(@RequestBody CommentDto comment) {
		return ResponseEntity.ok(interactionsManager.comment(comment));
	}

	/**
	 * For testing purposes only
	 */
	@PostMapping("/comments")
	public void comments(@RequestBody List<CommentDto> comments) {
		interactionsManager.saveComments(comments);
	}

	@DeleteMapping("/comment/{id}")
	public void deleteComment(@PathVariable UUID id) {
		interactionsManager.deleteCommentById(id);
	}

	@PostMapping("/like")
	public ResponseEntity<LikeDto> like(@RequestBody LikeDto like) {
		return ResponseEntity.ok(interactionsManager.like(like));
	}

	/**
	 * For testing purposes only
	 */
	@PostMapping("/likes")
	public void likes(@RequestBody List<LikeDto> likes) {
		interactionsManager.saveLikes(likes);
	}

	@DeleteMapping("/like")
	public void deleteLike(@RequestBody LikeDto like) {
		interactionsManager.deleteLikeBy(like.getUserId(), like.getPublicationId());
	}

	@DeleteMapping("/like/{id}")
	public void deleteLike(@PathVariable UUID id) {
		interactionsManager.deleteLikeById(id);
	}

	@GetMapping("/likes/{id}")
	public List<LikeDto> likesOfUser(@PathVariable UUID id) {
		return interactionsManager.likesOfUser(id);
	}

	@GetMapping("/-/likes/count/{id}")
	public int likeCountOfPublication(@PathVariable UUID id) {
		return interactionsManager.likesOfPublication(id).size();
	}

	@GetMapping("/-/comments/{id}")
	public List<CommentDto> commentsOfPublication(@PathVariable UUID id) {
		return interactionsManager.commentsOfPublication(id);
	}

	@GetMapping("/comments/count/{id}")
	public int commentsCountOfPublication(@PathVariable UUID id) {
		return interactionsManager.commentsOfPublication(id).size();
	}

	@GetMapping("/userliked/{userId}/{publicationId}")
	public boolean userLikedPublication(@PathVariable UUID userId, @PathVariable UUID publicationId) {
		return interactionsManager.likeExists(userId, publicationId);
	}

	@GetMapping("/usercommented/{userId}/{publicationId}")
	public boolean userCommentedPublication(@PathVariable UUID userId, @PathVariable UUID publicationId) {
		return interactionsManager.commentExists(userId, publicationId);
	}

	@PostMapping("/-/likes/ids")
	public Map<UUID, Integer> likesCountsByIds(@RequestBody List<UUID> idsList) {
		return interactionsManager.likesCountsByPublications(idsList);
	}

	@PostMapping("/-/comments/ids")
	public Map<UUID, List<CommentDto>> commentsByIds(@RequestBody List<UUID> idsList) {
		return interactionsManager.commentsByPublications(idsList);
	}
}
