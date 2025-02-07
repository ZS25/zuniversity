package com.sooruth.zuniversity.kafka.producer;

import com.sooruth.zuniversity.record.StudentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProducer.class);

    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final KafkaTemplate<String, StudentRecord> jsonKafkaTemplate;

    public MessageProducer(KafkaTemplate<String, String> stringKafkaTemplate, KafkaTemplate<String, StudentRecord> jsonKafkaTemplate) {
        this.stringKafkaTemplate = stringKafkaTemplate;
        this.jsonKafkaTemplate = jsonKafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        LOG.info(STR."Sending message: \{message} to topic: \{topic}");
        stringKafkaTemplate.send(topic, message);
    }

    public void sendMessage(String topic, StudentRecord studentRecord) {
        LOG.info(STR."Sending message: \{studentRecord} to topic: \{topic}");

        Message<StudentRecord> message = MessageBuilder
                .withPayload(studentRecord).setHeader(KafkaHeaders.TOPIC, topic).build();

        jsonKafkaTemplate.send(message);
    }
}