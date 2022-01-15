package com.java.library.gateway.kafka.producer.controller;

import com.java.library.gateway.kafka.config.property.KafkaAttributes;
import com.java.library.gateway.kafka.producer.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    // send messages to kafka
    private final KafkaTemplate<String, Object> kafkaTemplate;


    @PostMapping("/order/book")
    public void orderBook(@RequestBody Message message) throws Exception {

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaAttributes.TOPIC, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + message.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}