package com.microservices.webclient.service;

import com.microservices.dto.CurrencyConversion;
import com.microservices.dto.CurrencyExchange;
import com.microservices.openfeign.ext.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("CurrencyConversionServiceWebClient")
@Slf4j
public class CurrencyConversionService {

    CurrencyConversion currencyConversion;

    @Autowired
    WebClient webClient;

    public CurrencyConversion generateConversionAmt(String from, String to, int quantity) {
        CurrencyExchange currencyExchangeEntity = webClient.get()
                .uri("http://localhost:9081/currency-exchange/from/" + from + "/to/" + to)
                .retrieve()
                .bodyToMono(CurrencyExchange.class)
                .block();
        currencyConversion=new CurrencyConversion();
        currencyConversion.setFrom(from);
        currencyConversion.setTo(to);
        currencyConversion.setQuantity(quantity);
//        log.info("CurrencyExchange resttemplate call status: "+currencyExchangeEntity.getStatusCode());
//        if(currencyExchangeEntity.getStatusCode().is2xxSuccessful()){
            currencyConversion.setConversionMultiple(currencyExchangeEntity.getConversionMultiple());
            currencyConversion.setTotalCalculatedAmount(currencyExchangeEntity.getConversionMultiple()*quantity);
            currencyConversion.setEnvironment(currencyExchangeEntity.getEnvironment());
//        }
        return currencyConversion;
    }
}
