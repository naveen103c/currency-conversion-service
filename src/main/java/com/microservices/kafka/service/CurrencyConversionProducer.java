package com.microservices.kafka.service;

import com.microservices.dto.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyConversionProducer {
    private NewTopic topic;
    private KafkaTemplate<String, CurrencyExchange> kafkaTemplate;

    public CurrencyConversionProducer(NewTopic topic,KafkaTemplate<String, CurrencyExchange> kafkaTemplate){
        this.topic=topic;
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(CurrencyExchange currencyExchange) {
        Message<CurrencyExchange> message = MessageBuilder.withPayload(currencyExchange).setHeader(KafkaHeaders.TOPIC, topic.name()).build();
        kafkaTemplate.send(message);
    }
}
