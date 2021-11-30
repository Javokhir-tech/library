package com.java.library.gateway.kafka.consumer.service;

import com.java.library.gateway.kafka.producer.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_Example", groupId = "tpd-loggers")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "Kafka_Example", groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Message user) {
        System.out.println("Consumed JSON Message: " + user);
    }
}
