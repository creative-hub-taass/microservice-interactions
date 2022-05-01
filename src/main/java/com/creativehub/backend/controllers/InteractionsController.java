package com.creativehub.backend.controllers;

import com.creativehub.backend.models.Comment;
import com.creativehub.backend.models.Like;
import com.creativehub.backend.services.impl.InteractionsManagerImpl;
import com.creativehub.backend.util.InteractionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/interactions")
@RequiredArgsConstructor
public class InteractionsController {
    private final InteractionsManagerImpl interactionsManager;

    @PostMapping("/comment")
    public ResponseEntity<?> comment(@RequestBody Comment comment) {
        try {
            interactionsManager.comment(comment);
            return ResponseEntity.ok("OK");
        } catch (InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id) {
        try {
            interactionsManager.deleteCommentById(id);
            return ResponseEntity.ok("OK");
        } catch (InteractionException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody Like like) {
        try {
            interactionsManager.like(like);
            return ResponseEntity.ok("OK");
        } catch (InteractionException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @DeleteMapping("/like/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable UUID id) {
        try {
            interactionsManager.deleteLikeById(id);
            return ResponseEntity.ok("OK");
        } catch (InteractionException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("likes/{id}")
    public List<Like> likesOfUser(@PathVariable UUID id) {
        return interactionsManager.likesOfUser(id);
    }

    @GetMapping("likes/count/{id}")
    public int likeCountOfPublication(@PathVariable UUID id) {
        return interactionsManager.likesOfPublication(id).size();
    }

    @GetMapping("comments/{id}")
    public List<Comment> commentsOfPublication(@PathVariable UUID id) {
        return interactionsManager.commentsOfPublication(id);
    }

    @GetMapping("comments/count/{id}")
    public int commentsCountOfPublication(@PathVariable UUID id) {
        return interactionsManager.commentsOfPublication(id).size();
    }

    //FIXME: magari mettere come parametri
    @GetMapping("userliked/{userId}/{publicationId}")
    public boolean userLikedPublication(@PathVariable UUID userId, @PathVariable UUID publicationId) {
        return interactionsManager.likeExists(userId, publicationId);
    }

    //FIXME: magari mettere come parametri
    @GetMapping("usercommented/{userId}/{publicationId}")
    public boolean userCommentedPublication(@PathVariable UUID userId, @PathVariable UUID publicationId) {
        return interactionsManager.commentExists(userId, publicationId);
    }

    @PostMapping("/likes/-/ids")
    public Map<String, Object> likesCountsByIds(@RequestBody List<UUID> idsList) {
        return interactionsManager.likesCountsByPublications(idsList);
    }

    @PostMapping("/comments/-/ids")
    public  Map<String, Object> commentsByIds(@RequestBody List<UUID> idsList) {
        return interactionsManager.commentsByPublications(idsList);
    }
}
