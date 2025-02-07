package com.sooruth.zuniversity.kafka.consumer;

import com.sooruth.zuniversity.record.StudentRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "zmessage-topic", groupId = "${spring.kafka.consumer.group-id}", containerFactory ="stringKafkaListenerContainerFactory")
    public void listenMessage(String message) {
        LOG.info(STR."Received message: \{message}");
    }

    @KafkaListener(topics = "${zuni.topic}", groupId = "${spring.kafka.consumer.group-id}", containerFactory ="stringKafkaListenerContainerFactory")
    public void listenDefaultTopic(String message) {
        LOG.info(STR."Received message: \{message}");
    }

    @KafkaListener(topics = "zuni-student-topic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "jsonKafkaListenerContainerFactory")
    public void listenStudentRecord(StudentRecord studentRecord) {
        LOG.info(STR."Received record: \{studentRecord}");
    }

}