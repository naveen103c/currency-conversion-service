package com.microservices.service;

import com.microservices.dto.CurrencyConversion;
import com.microservices.dto.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CurrencyConversionService {

    CurrencyConversion currencyConversion;

    @Autowired
    RestTemplate restTemplate;
    public CurrencyConversion generateConversionAmt(String from, String to, int quantity) {
        CurrencyExchange currencyExchange = restTemplate.getForObject("http://localhost:9081/currency-exchange/from/"+from+"/to/"+to, CurrencyExchange.class);
        log.info(String.valueOf(currencyExchange));
        currencyConversion=new CurrencyConversion();
        currencyConversion.setFrom(from);
        currencyConversion.setTo(to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setConversionMultiple(currencyExchange.getConversionMultiple());
        currencyConversion.setTotalCalculatedAmount(currencyExchange.getConversionMultiple()*quantity);
        currencyConversion.setEnvironment(currencyExchange.getEnvironment());
        return currencyConversion;
    }
}
