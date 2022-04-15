package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.CollaborationRequest;
import com.creativehub.backend.services.dto.CollaborationRequestDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CollaborationRequestMapper {
    CollaborationRequest collaborationRequestDtoToCollaborationRequest(CollaborationRequestDto collaborationRequestDto);

    CollaborationRequestDto collaborationRequestToCollaborationRequestDto(CollaborationRequest collaborationRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCollaborationRequestFromCollaborationRequestDto(CollaborationRequestDto collaborationRequestDto, @MappingTarget CollaborationRequest collaborationRequest);
}
