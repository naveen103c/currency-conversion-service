package com.microservices.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "testTopic",groupId = "test_group")
    public void listenToTopic(String receivedMessage){
        System.out.println("Received Message is : "+receivedMessage);
    }
}
