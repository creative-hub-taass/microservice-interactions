package com.creativehub.backend.services;

import com.creativehub.backend.models.CollaborationRequest;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import com.creativehub.backend.services.dto.CollaborationRequestDto;

import java.util.List;
import java.util.UUID;

public interface CollaborationRequestManager {
	CollaborationRequestDto addCollaboration(CollaborationRequest request);

	void updateCollaborationStatus(UUID id, CollaborationRequestStatus status);

	List<CollaborationRequestDto> getAllOpenRequestsBySenderId(UUID id);

	List<CollaborationRequestDto> getAllOpenRequestsByReceiverId(UUID id);

	List<CollaborationRequestDto> getAllOpenBroadcastRequests();

	List<CollaborationRequestDto> getAllOpenBroadcastRequestsByUserId(UUID id);

	void deleteByUserId(UUID userId);

}
