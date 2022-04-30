package com.creativehub.backend.services.impl;

import com.creativehub.backend.models.Comment;
import com.creativehub.backend.models.Like;
import com.creativehub.backend.repositories.CommentRepository;
import com.creativehub.backend.repositories.LikeRepository;
import com.creativehub.backend.services.InteractionsManager;
import com.creativehub.backend.util.InteractionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InteractionsManagerImpl implements InteractionsManager {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public boolean likeExists(UUID userId, UUID publicationId) {
        return !likeRepository.likeByUserPublicationIds(userId, publicationId).isEmpty();
    }

    public boolean commentExists(UUID userId, UUID publicationId) {
        return !commentRepository.commentByUserPublicationIds(userId, publicationId).isEmpty();
    }

    @Override
    public void like(Like like) throws InteractionException {
        if (likeExists(like.getUserId(), like.getPublicationId())) throw new InteractionException("NOT_FOUND");
        else likeRepository.save(like);

    }

    @Override
    public void deleteLikeById(UUID likeId) throws InteractionException {
        if (likeRepository.existsById(likeId)) likeRepository.deleteById(likeId);
        else throw new InteractionException("NOT_FOUND");
    }

    @Override
    public List<Like> likesOfUser(UUID userId) {
        return likeRepository.likesByUserId(userId);
    }

    @Override
    public List<Like> likesOfPublication(UUID publicationId) {
        return likeRepository.likesByPublicationId(publicationId);
    }

    public Map<String, Object>  likesCountsByPublications(List<UUID> publicationsIds) {
        Map<String, Object> json = new HashMap<>();
        for (UUID id : publicationsIds) {json.put(id.toString(), likesOfPublication(id).size());}
        return json;
    }

    public  Map<String, Object> commentsByPublications(List<UUID> publicationsIds) {
        Map<String, Object> json = new HashMap<>();
        for (UUID id : publicationsIds) {json.put(id.toString(), commentsOfPublication(id));}
        return json;
    }

    @Override
    public List<Comment> commentsOfPublication(UUID publicationId) {
        return commentRepository.commentsByPublicationId(publicationId);
    }

    @Override
    public void comment(Comment comment) throws InterruptedException {
        if (commentExists(comment.getUserId(), comment.getPublicationId())) throw new InterruptedException("NOT_FOUND");
        else commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(UUID commentId) throws InteractionException {
        if (commentRepository.existsById(commentId)) commentRepository.deleteById(commentId);
        else throw new InteractionException("NOT_FOUND");
    }
}
