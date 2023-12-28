package com.microservices.kafka.controller;

import com.microservices.dto.CurrencyExchange;
import com.microservices.kafka.service.CurrencyConversionProducer;
import com.microservices.kafka.service.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CurrencyConversionControllerKafka")
@Slf4j
@RequestMapping("/currency-conversion-kafka")
public class CurrencyConversionController {

    @Autowired
    Producer producer;

    @Autowired
    CurrencyConversionProducer currencyConversionProducer;
    public CurrencyConversionController(CurrencyConversionProducer currencyConversionProducer){
        this.currencyConversionProducer=currencyConversionProducer;
    }

    @PostMapping("/currency-exchange/add")
    public ResponseEntity<CurrencyExchange> addExchnageValue(@RequestBody CurrencyExchange currencyExchange){
        log.info("adding ExchnageValue");
        currencyExchange.setId(5L);
        currencyExchange.setCurrency_from("YEN");
        currencyExchange.setCurrency_to("INR");
        currencyExchange.setConversionMultiple(34);
        currencyExchange.setEnvironment("9092");
        currencyConversionProducer.sendMessage(currencyExchange);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/kafkatest")
    public void sendMessagetoClient(@RequestParam("message") String message){
        producer.sendMessageToTopic(message);
    }
}
