package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Like;
import com.creativehub.backend.services.dto.LikeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LikeMapper {
	Like likeDtoToLike(LikeDto likeDto);

	LikeDto likeToLikeDto(Like like);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateLikeFromLikeDto(LikeDto likeDto, @MappingTarget Like like);
}
