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

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth/interactions")
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
}
