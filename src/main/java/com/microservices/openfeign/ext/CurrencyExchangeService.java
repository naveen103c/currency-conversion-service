package com.microservices.openfeign.ext;

import com.microservices.dto.CurrencyExchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CURRENCY-EXCHANGE-SERVICE", value = "CURRENCY-EXCHANGE-SERVICE", url = "http://localhost:9081/currency-exchange")
public interface CurrencyExchangeService {

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> retrieveExchnageValue(@PathVariable String from, @PathVariable String to);
}
