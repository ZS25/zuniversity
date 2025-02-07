package com.sooruth.zuniversity.kafka;

import com.sooruth.zuniversity.kafka.producer.MessageProducer;
import com.sooruth.zuniversity.record.StudentRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private String topic;
    private MessageProducer messageProducer;

    public KafkaController(@Value("${zuni.topic}") String topic, MessageProducer messageProducer) {
        this.topic = topic;
        this.messageProducer = messageProducer;
    }

    @PostMapping("/sendWithTopic")
    public String sendMessageWithTopic(@RequestParam String topic, @RequestParam String message) {
        messageProducer.sendMessage(topic, message);
        return STR."Message sent: \{message}";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        messageProducer.sendMessage(topic, message);
        return STR."Message sent: \{message}";
    }

    @PostMapping("/sendStudentRecord")
    public String sendStudentRecord(@RequestParam String topic, @RequestBody StudentRecord studentRecord) {
        messageProducer.sendMessage(topic, studentRecord);
        return STR."Record sent: \{studentRecord}";
    }
}