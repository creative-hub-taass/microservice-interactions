package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.CollaborationRequestManager;
import com.creativehub.backend.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
	private final CollaborationRequestManager collaborationRequestManager;

	@Override
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void receivedMessage(UUID id) {
		//eliminare le richieste collaborazione attive verso e dall'utente con id passato per parametro
		collaborationRequestManager.deleteByUserId(id);
	}
}
