package com.microservices.resttemplate.controller;

import com.microservices.dto.CurrencyConversion;
import com.microservices.resttemplate.service.CurrencyConversionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    @Autowired
    private Environment env;

    @Autowired
    CurrencyConversionService currencyConversionService;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    @CircuitBreaker(name = "conversionExchangeCircuitBreaker",fallbackMethod = "conversionExchangeFallbackMethod")
    public ResponseEntity<CurrencyConversion> retrieveConvertedValue(@PathVariable String from, @PathVariable String to, @PathVariable int quantity){
        log.info("retrieve Currency conversion from-"+from+" ,to-"+to);
        CurrencyConversion currencyConversion = currencyConversionService.generateConversionAmt(from,to,quantity);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(currencyConversion);
    }
    //creating fallback method
    public ResponseEntity<CurrencyConversion> conversionExchangeFallbackMethod(String from, String to, int quantity, Exception ex){
        log.info("called conversionExchangeFallbackMethod method is called. Service Down: " + ex.getMessage());
        return new ResponseEntity<>(HttpStatusCode.valueOf(503));
    }
}
