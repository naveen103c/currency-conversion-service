package com.microservices.webclient.controller;

import com.microservices.dto.CurrencyConversion;
import com.microservices.webclient.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("CurrencyConversionControllerWebClient")
@Slf4j
@RequestMapping("/currency-conversion-webclient")
public class CurrencyConversionController {

    @Autowired
    @Qualifier(value = "CurrencyConversionServiceWebClient")
    CurrencyConversionService currencyConversionService;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveConvertedValue(@PathVariable String from, @PathVariable String to, @PathVariable int quantity){
        log.info("retrieve Currency conversion from-"+from+" ,to-"+to);
        CurrencyConversion currencyConversion = currencyConversionService.generateConversionAmt(from,to,quantity);
        return currencyConversion;
    }
}
