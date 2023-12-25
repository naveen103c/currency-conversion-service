package com.microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchange {
    private Long id;
    private String currency_from;
    private String currency_to;
    private double conversionMultiple;
    private String environment;
}