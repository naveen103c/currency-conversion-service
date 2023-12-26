package com.microservices.openfeign.service;

import com.microservices.dto.CurrencyConversion;
import com.microservices.dto.CurrencyExchange;
import com.microservices.openfeign.ext.CurrencyExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("CurrencyConversionServiceFeign")
@Slf4j
public class CurrencyConversionService {

    CurrencyConversion currencyConversion;

    @Autowired
    CurrencyExchangeService currencyExchangeService;

    public CurrencyConversion generateConversionAmt(String from, String to, int quantity) {
        ResponseEntity<CurrencyExchange> currencyExchangeEntity=currencyExchangeService.retrieveExchnageValue(from, to);
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
