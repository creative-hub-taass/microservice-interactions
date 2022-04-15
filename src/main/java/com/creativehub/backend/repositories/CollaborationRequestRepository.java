package com.creativehub.backend.repositories;

import com.creativehub.backend.models.CollaborationRequest;
import com.creativehub.backend.models.enums.CollaborationRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface CollaborationRequestRepository extends JpaRepository<CollaborationRequest, UUID> {

    //TODO: aggiungere query per ottenere tutte le richieste aperte
    @Query("SELECT r FROM CollaborationRequest r WHERE r.receiverId = ?1 OR r.senderId = ?1 AND r.status = 'OPEN'")
    List<CollaborationRequest> getAllOpenRequests(UUID userId);

    @Transactional
    @Modifying
    @Query("UPDATE CollaborationRequest r SET r.status = ?2 WHERE r.id = ?1")
    void updateRequestStatus(UUID id, CollaborationRequestStatus status);

    @Query("SELECT r FROM CollaborationRequest r WHERE r.senderId =?1 AND r.status='OPEN'")
    List<CollaborationRequest> findAllOpenRequestsBySenderId(UUID id);

    // TODO: più artisti possono partecipare?
    @Query("SELECT r FROM CollaborationRequest r WHERE r.senderId = r.receiverId AND r.status='OPEN'")
    List<CollaborationRequest> findAllOpenBroadcastRequests();
}
