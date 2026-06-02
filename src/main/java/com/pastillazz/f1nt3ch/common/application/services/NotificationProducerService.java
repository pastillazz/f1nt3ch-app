package com.pastillazz.f1nt3ch.common.application.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducerService
{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic,String key, Object data)
    {
        log.info("Sending message to topic: {}, key: {}, data: {}", topic, key, data);
        kafkaTemplate.send(topic, key, data);
    }
}
