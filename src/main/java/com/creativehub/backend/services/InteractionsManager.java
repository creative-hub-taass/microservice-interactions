package com.creativehub.backend.services;

import com.creativehub.backend.models.Comment;
import com.creativehub.backend.models.Like;
import com.creativehub.backend.util.InteractionException;

import java.util.UUID;

public interface InteractionsManager {
    public void like(Like like) throws InteractionException;
    public void deleteLikeById(UUID likeId) throws InteractionException;
    public void comment(Comment comment) throws InterruptedException;
    public void deleteCommentById(UUID commentId) throws InteractionException;
}
