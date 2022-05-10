package com.creativehub.backend.controllers;

import com.creativehub.backend.models.CollaborationRequest;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import com.creativehub.backend.services.CollaborationRequestManager;
import com.creativehub.backend.services.dto.CollaborationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/interactions/collabs")
@RequiredArgsConstructor
public class CollaborationRequestController {
	private final CollaborationRequestManager collaborationRequestManager;

	@PostMapping("/request")
	public CollaborationRequestDto addCollaborationRequest(@RequestBody CollaborationRequest collaborationRequest) {
		return collaborationRequestManager.addCollaboration(collaborationRequest);
	}

	@GetMapping("/request/close/{id}")
	public void rejectCollaborationRequest(@PathVariable UUID id) {
		collaborationRequestManager.updateCollaborationStatus(id, CollaborationRequestStatus.CLOSED);
	}

	@GetMapping("requests/sender/{id}")
	public List<CollaborationRequestDto> getAllOpenRequestsBySenderId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllOpenRequestsBySenderId(id);
	}

	@GetMapping("requests/receiver/{id}")
	public List<CollaborationRequestDto> getAllOpenRequestsByReceiverId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllOpenRequestsByReceiverId(id);
	}

	@GetMapping("requests/broadcast")
	public List<CollaborationRequestDto> getAllOpenBroadcastRequests() {
		return collaborationRequestManager.getAllOpenBroadcastRequests();
	}

	@GetMapping("requests/broadcast/{id}")
	public List<CollaborationRequestDto> getAllOpenBroadcastRequestsByUserId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllOpenBroadcastRequestsByUserId(id);
	}
}
