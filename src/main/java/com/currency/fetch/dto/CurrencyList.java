package com.currency.fetch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor

public class CurrencyList {
    private int amount;

    private String baseCurrency;

    private String data;

    private Map<String,Double> rates;

}
