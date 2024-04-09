package com.sooruth.zuniversity.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "zuni-topic", groupId = "zuni-group-id")
    public void listen(String message) {
        LOG.info(STR."Received message: \{message}");
    }
}