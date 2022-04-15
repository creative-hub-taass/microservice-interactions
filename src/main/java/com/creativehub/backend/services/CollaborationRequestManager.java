package com.creativehub.backend.services;

import com.creativehub.backend.models.CollaborationRequest;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import com.creativehub.backend.services.dto.CollaborationRequestDto;

import java.util.List;
import java.util.UUID;

public interface CollaborationRequestManager {
    CollaborationRequestDto addCollaboration(CollaborationRequest request);
    void updateCollaborationStatus(UUID id, CollaborationRequestStatus status);
    List<CollaborationRequestDto> getAllOpenRequestBySenderId(UUID id);

    // TODO: aggiungere collab broadcast
}
