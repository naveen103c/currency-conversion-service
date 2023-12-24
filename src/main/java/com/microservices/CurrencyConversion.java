package com.microservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CurrencyConversion {
    private Long id;
    private String from;
    private String to;
    private double conversionMultiple;
    private double quantity;
    private double totalCalculatedAmount;
    private String environment;
}
