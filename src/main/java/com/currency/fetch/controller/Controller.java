package com.currency.fetch.controller;


import com.currency.fetch.dto.CurrencyList;
import com.currency.fetch.service.CurrencyFetchService;
import com.currency.fetch.service.KafkaPublishSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Currency Fetch API", description = "APIs for fetching currency rates and publishing to Kafka")
public class Controller {

    @Autowired
    private CurrencyFetchService currencyFetchService;

    @Autowired
    private KafkaPublishSerivce kafkaPublishSerivce;

    @Operation(
            summary = "Trigger scheduled currency fetch",
            description = "Manually triggers the scheduled method that fetches currency rates with USD as base currency and publishes to Kafka"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Scheduled method triggered successfully",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping(value = "/ping")
    public String triggerScheduledMethod(){
        currencyFetchService.fetchCurrencyListScheduled();
        return "Scheduled method triggered successfully";
    }

    @Operation(
            summary = "Publish currency data to Kafka",
            description = "Manually publishes a currency list message directly to the Kafka topic 'currency-rates'"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message published to Kafka topic successfully",
                    content = @Content(schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Failed to publish message",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping(value = "/publish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> publishToKafka(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Currency list object containing amount, base currency, date, and exchange rates",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CurrencyList.class))
            )
            @RequestBody CurrencyList currencyList){
        try {
            kafkaPublishSerivce.publish(currencyList);
            return ResponseEntity.ok("Message published to Kafka topic successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to publish message: " + e.getMessage());
        }
    }
}
