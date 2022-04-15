package com.creativehub.backend.repositories;

import com.creativehub.backend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query("SELECT c FROM Comment c WHERE c.userId = ?1 AND c.publicationId = ?2")
    List<Comment> commentByUserPublicationIds(UUID userId, UUID publicationId);

}
