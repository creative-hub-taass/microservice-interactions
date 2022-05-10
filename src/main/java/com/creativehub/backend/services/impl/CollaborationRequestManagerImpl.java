package com.creativehub.backend.services.impl;

import com.creativehub.backend.models.CollaborationRequest;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import com.creativehub.backend.repositories.CollaborationRequestRepository;
import com.creativehub.backend.services.CollaborationRequestManager;
import com.creativehub.backend.services.dto.CollaborationRequestDto;
import com.creativehub.backend.services.mapper.CollaborationRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollaborationRequestManagerImpl implements CollaborationRequestManager {
	private final CollaborationRequestMapper collaborationRequestMapper;
	private final CollaborationRequestRepository collaborationRequestRepository;

	@Override
	public void deleteByUserId(UUID userId) {
		collaborationRequestRepository.deleteByUserId(userId);
	}

	@Override
	public CollaborationRequestDto addCollaboration(CollaborationRequest request) {
		return collaborationRequestMapper.collaborationRequestToCollaborationRequestDto(collaborationRequestRepository.save(request));
	}

	@Override
	public void updateCollaborationStatus(UUID id, CollaborationRequestStatus status) {
		collaborationRequestRepository.updateRequestStatus(id, status);
	}

	@Override
	public List<CollaborationRequestDto> getAllOpenRequestsBySenderId(UUID id) {
		return collaborationRequestRepository.findAllOpenRequestsBySenderId(id).stream().map(collaborationRequestMapper::collaborationRequestToCollaborationRequestDto).collect(Collectors.toList());
	}

	@Override
	public List<CollaborationRequestDto> getAllOpenRequestsByReceiverId(UUID id) {
		return collaborationRequestRepository.findAllOpenRequestsByReceiverId(id).stream().map(collaborationRequestMapper::collaborationRequestToCollaborationRequestDto).collect(Collectors.toList());
	}

	@Override
	public List<CollaborationRequestDto> getAllOpenBroadcastRequests() {
		return collaborationRequestRepository.findAllOpenBroadcastRequests().stream().map(collaborationRequestMapper::collaborationRequestToCollaborationRequestDto).collect(Collectors.toList());
	}

	@Override
	public List<CollaborationRequestDto> getAllOpenBroadcastRequestsByUserId(UUID id) {
		return collaborationRequestRepository.findAllOpenBroadcastRequestsByUserId(id).stream().map(collaborationRequestMapper::collaborationRequestToCollaborationRequestDto).collect(Collectors.toList());
	}

}
