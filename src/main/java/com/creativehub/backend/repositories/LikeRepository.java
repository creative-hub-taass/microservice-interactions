package com.creativehub.backend.repositories;

import com.creativehub.backend.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Like, UUID> {

	@Query("SELECT l FROM Like l WHERE l.userId = ?1 AND l.publicationId = ?2")
	List<Like> likeByUserPublicationIds(UUID userId, UUID publicationId);

	@Query("SELECT l from Like l WHERE l.publicationId = ?1")
	List<Like> likesByPublicationId(UUID id);

	@Query("SELECT l from Like l WHERE l.userId = ?1")
	List<Like> likesByUserId(UUID id);
}