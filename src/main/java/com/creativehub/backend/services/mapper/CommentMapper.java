package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Comment;
import com.creativehub.backend.services.dto.CommentDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CommentMapper {
	Comment commentDtoToComment(CommentDto commentDto);

	CommentDto commentToCommentDto(Comment comment);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateCommentFromCommentDto(CommentDto commentDto, @MappingTarget Comment comment);
}
