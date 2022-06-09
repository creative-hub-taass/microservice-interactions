package com.creativehub.backend.services.impl;

import com.creativehub.backend.models.Like;
import com.creativehub.backend.repositories.CommentRepository;
import com.creativehub.backend.repositories.LikeRepository;
import com.creativehub.backend.services.InteractionsManager;
import com.creativehub.backend.services.dto.CommentDto;
import com.creativehub.backend.services.dto.LikeDto;
import com.creativehub.backend.services.mapper.CommentMapper;
import com.creativehub.backend.services.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class InteractionsManagerImpl implements InteractionsManager {
	private final CommentRepository commentRepository;
	private final LikeRepository likeRepository;
	private final CommentMapper commentMapper;
	private final LikeMapper likeMapper;

	@Override
	public boolean likeExists(UUID userId, UUID publicationId) {
		return !likeRepository.likeByUserPublicationIds(userId, publicationId).isEmpty();
	}

	@Override
	public boolean commentExists(UUID userId, UUID publicationId) {
		return !commentRepository.commentByUserPublicationIds(userId, publicationId).isEmpty();
	}

	@Override
	public LikeDto like(LikeDto like) {
		List<Like> likes = likeRepository.likeByUserPublicationIds(like.getUserId(), like.getPublicationId());
		if (likes.isEmpty()) {
			return likeMapper.likeToLikeDto(likeRepository.save(likeMapper.likeDtoToLike(like)));
		} else return likeMapper.likeToLikeDto(likes.get(0));
	}

	@Override
	public void deleteLikeBy(UUID userId, UUID publicationId) {
		likeRepository.deleteAll(likeRepository.likeByUserPublicationIds(userId, publicationId));
	}

	@Override
	public void deleteLikesByUserId(UUID userId) {
		likeRepository.deleteByUserId(userId);
	}

	@Override
	public void deleteLikeById(UUID likeId) {
		likeRepository.findById(likeId).ifPresent(likeRepository::delete);
	}

	@Override
	public List<LikeDto> likesOfUser(UUID userId) {
		return likeRepository.likesByUserId(userId).stream().map(likeMapper::likeToLikeDto).collect(Collectors.toList());
	}

	@Override
	public List<LikeDto> likesOfPublication(UUID publicationId) {
		return likeRepository.likesByPublicationId(publicationId).stream().map(likeMapper::likeToLikeDto).collect(Collectors.toList());
	}

	@Override
	public Map<UUID, Integer> likesCountsByPublications(List<UUID> publicationsIds) {
		return publicationsIds.stream().collect(toMap(uuid -> uuid, uuid -> likesOfPublication(uuid).size()));
	}

	@Override
	public Map<UUID, List<CommentDto>> commentsByPublications(List<UUID> publicationsIds) {
		return publicationsIds.stream().collect(toMap(uuid -> uuid, this::commentsOfPublication));
	}

	@Override
	public void saveLikes(List<LikeDto> likes) {
		likeRepository.saveAll(likes.stream().map(likeMapper::likeDtoToLike).collect(Collectors.toList()));
	}

	@Override
	public void saveComments(List<CommentDto> comments) {
		commentRepository.saveAll(comments.stream().map(commentMapper::commentDtoToComment).collect(Collectors.toList()));
	}

	@Override
	public List<CommentDto> commentsOfPublication(UUID publicationId) {
		return commentRepository.commentsByPublicationId(publicationId).stream().map(commentMapper::commentToCommentDto).collect(Collectors.toList());
	}

	@Override
	public CommentDto comment(CommentDto comment) {
		return commentMapper.commentToCommentDto(commentRepository.save(commentMapper.commentDtoToComment(comment)));
	}

	@Override
	public void deleteCommentById(UUID commentId) {
		commentRepository.findById(commentId).ifPresent(commentRepository::delete);
	}

	@Override
	public void deleteCommentsByUserId(UUID userId) {
		commentRepository.deleteByUserId(userId);
	}
}
