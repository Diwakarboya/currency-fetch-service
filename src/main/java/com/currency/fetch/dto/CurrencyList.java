package com.currency.fetch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyList {
    private int amount;

    private String base;

    private String date;

    private Map<String,Double> rates;

}
