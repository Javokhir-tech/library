package com.java.library.gateway.kafka.consumer.service;

import com.java.library.gateway.kafka.config.property.KafkaAttributes;
import com.java.library.gateway.kafka.producer.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.java.library.gateway.kafka.config.property.KafkaAttributes.*;


@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Message user) {
        System.out.println("Consumed JSON Message: " + user);
    }
}
