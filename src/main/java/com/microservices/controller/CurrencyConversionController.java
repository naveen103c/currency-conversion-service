package com.microservices.controller;

import com.microservices.dto.CurrencyConversion;
import com.microservices.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.logging.Logger;

@RestController
@Slf4j
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private Environment env;

    @Autowired
    CurrencyConversionService currencyConversionService;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveConvertedValue(@PathVariable String from, @PathVariable String to, @PathVariable int quantity){
        log.info("retrieve Currency conversion from-"+from+" ,to-"+to);
        CurrencyConversion currencyConversion = currencyConversionService.generateConversionAmt(from,to,quantity);
        return currencyConversion;
    }
}
