package com.creativehub.backend.controllers;

import com.creativehub.backend.models.CollaborationRequest;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import com.creativehub.backend.services.dto.CollaborationRequestDto;
import com.creativehub.backend.services.impl.CollaborationRequestManagerImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/interactions/collabs")
@RequiredArgsConstructor
public class CollaborationRequestController {
    private final CollaborationRequestManagerImp collaborationRequestManager;

    @PostMapping("/request")
    public CollaborationRequestDto addCollaborationRequest(@RequestBody CollaborationRequest collaborationRequest) {
        return collaborationRequestManager.addCollaboration(collaborationRequest);
    }

    @GetMapping("/request/close/{id}")
    public void rejectCollaborationRequest(@PathVariable UUID id) {
        collaborationRequestManager.updateCollaborationStatus(id, CollaborationRequestStatus.CLOSED);
    }

    @GetMapping("requests/{id}")
    public List<CollaborationRequestDto> getAllOpenRequestBySenderId(@PathVariable UUID id) {
        return collaborationRequestManager.getAllOpenRequestBySenderId(id);
    }

    //TODO: aggiungere aperte di broadcast
    //TODO: anche per receiver ID

}
