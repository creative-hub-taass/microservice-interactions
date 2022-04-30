package com.creativehub.backend.services;

import com.creativehub.backend.models.Comment;
import com.creativehub.backend.models.Like;
import com.creativehub.backend.util.InteractionException;

import java.util.List;
import java.util.UUID;

public interface InteractionsManager {
    // LIKES
    void like(Like like) throws InteractionException;
    void deleteLikeById(UUID likeId) throws InteractionException;
    List<Like> likesOfUser(UUID userId);
    List<Like> likesOfPublication(UUID userId);
    // COMMENTS
    void comment(Comment comment) throws InterruptedException;
    void deleteCommentById(UUID commentId) throws InteractionException;
    List<Comment> commentsOfPublication(UUID publicationId);
}
