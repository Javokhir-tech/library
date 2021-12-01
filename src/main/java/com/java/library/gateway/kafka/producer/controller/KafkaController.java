package com.java.library.gateway.kafka.producer.controller;

import com.java.library.gateway.kafka.producer.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    // send messages to kafka
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic-name}")
    private final String TOPIC;

    @GetMapping("/order/{book}")
    public void hello(@PathVariable("msg") String message) throws Exception {

        var msg = new Message(message);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + msg.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + msg.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}