package com.currency.fetch.controller;


import com.currency.fetch.dto.CurrencyList;
import com.currency.fetch.service.CurrencyFetchService;
import com.currency.fetch.service.KafkaPublishSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @Autowired
    private CurrencyFetchService currencyFetchService;

    @Autowired
    private KafkaPublishSerivce kafkaPublishSerivce;

    @GetMapping(value = "/ping")
    public String getCurrencyList(@RequestParam String currency){
        currencyFetchService.getCurrencyListWithBase(currency);
        return "message published";
    }
}
