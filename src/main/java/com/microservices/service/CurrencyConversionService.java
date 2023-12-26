package com.microservices.service;

import com.microservices.dto.CurrencyConversion;
import com.microservices.dto.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CurrencyConversionService {

    CurrencyConversion currencyConversion;

    @Autowired
    RestTemplate restTemplate;
    public CurrencyConversion generateConversionAmt(String from, String to, int quantity) {
//        getForObject
//        CurrencyExchange currencyExchange = restTemplate.getForObject("http://localhost:9081/currency-exchange/from/"+from+"/to/"+to, CurrencyExchange.class);
//        getForEntity
        ResponseEntity<CurrencyExchange> currencyExchangeEntity = restTemplate.getForEntity("http://localhost:9081/currency-exchange/from/" + from + "/to/" + to, CurrencyExchange.class);
        currencyConversion=new CurrencyConversion();
        currencyConversion.setFrom(from);
        currencyConversion.setTo(to);
        currencyConversion.setQuantity(quantity);
        log.info("CurrencyExchange resttemplate call status: "+currencyExchangeEntity.getStatusCode());
        if(currencyExchangeEntity.getStatusCode().is2xxSuccessful()){
            currencyConversion.setConversionMultiple(currencyExchangeEntity.getBody().getConversionMultiple());
            currencyConversion.setTotalCalculatedAmount(currencyExchangeEntity.getBody().getConversionMultiple()*quantity);
            currencyConversion.setEnvironment(currencyExchangeEntity.getBody().getEnvironment());
        }
        return currencyConversion;
    }
}
