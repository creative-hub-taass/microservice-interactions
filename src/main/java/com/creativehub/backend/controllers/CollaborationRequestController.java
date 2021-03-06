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
		return collaborationRequestManager.saveCollaborationRequest(collaborationRequest);
	}

	@GetMapping("/request/close/{id}")
	public void rejectCollaborationRequest(@PathVariable UUID id) {
		collaborationRequestManager.updateCollaborationStatus(id, CollaborationRequestStatus.CLOSED);
	}

	@GetMapping("requests/sender/open/{id}")
	public List<CollaborationRequestDto> getAllOpenRequestsBySenderId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllOpenRequestsBySenderId(id);
	}

	@GetMapping("requests/sender/closed/{id}")
	public List<CollaborationRequestDto> getAllClosedRequestsBySenderId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllClosedRequestsBySenderId(id);
	}

	@GetMapping("requests/receiver/open/{id}")
	public List<CollaborationRequestDto> getAllOpenRequestsByReceiverId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllOpenRequestsByReceiverId(id);
	}

	@GetMapping("requests/receiver/closed/{id}")
	public List<CollaborationRequestDto> getAllClosedRequestsByReceiverId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllClosedRequestsByReceiverId(id);
	}

	@GetMapping("requests/broadcast/open")
	public List<CollaborationRequestDto> getAllOpenBroadcastRequests() {
		return collaborationRequestManager.getAllOpenBroadcastRequests();
	}

	@GetMapping("requests/broadcast/closed")
	public List<CollaborationRequestDto> getAllClosedBroadcastRequests() {
		return collaborationRequestManager.getAllClosedBroadcastRequests();
	}

	@GetMapping("requests/broadcast/open/{id}")
	public List<CollaborationRequestDto> getAllOpenBroadcastRequestsByUserId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllOpenBroadcastRequestsByUserId(id);
	}

	@GetMapping("requests/broadcast/closed/{id}")
	public List<CollaborationRequestDto> getAllClosedBroadcastRequestsByUserId(@PathVariable UUID id) {
		return collaborationRequestManager.getAllClosedBroadcastRequestsByUserId(id);
	}
}
