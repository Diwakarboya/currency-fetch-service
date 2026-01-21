package com.currency.fetch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Currency exchange rates data model")
public class CurrencyList {
    
    @Schema(description = "Amount of base currency", example = "1", required = true)
    private int amount;

    @Schema(description = "Base currency code", example = "USD", required = true)
    private String base;

    @Schema(description = "Date of the exchange rates in YYYY-MM-DD format", example = "2024-01-15", required = true)
    private String date;

    @Schema(description = "Map of currency codes to their exchange rates relative to base currency", 
            example = "{\"EUR\": 0.92, \"GBP\": 0.79, \"INR\": 83.5}", required = true)
    private Map<String,Double> rates;

}
