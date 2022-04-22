package com.creativehub.backend.services.impl;

import com.creativehub.backend.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    //repository

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(UUID id) {
        //TODO
        //eliminare le richieste collaborazione attive verso e dall'utente con id passato per parametro
    }
}
