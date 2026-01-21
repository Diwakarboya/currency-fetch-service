package com.currency.fetch.service;


import com.currency.fetch.dto.CurrencyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyFetchService {

    private final WebClient webClient = WebClient.create("https://api.frankfurter.dev/v1");

    @Autowired
    private KafkaPublishSerivce kafkaPublishSerivce;

    @Scheduled(cron = "0 0/30 * * * *")
    public void fetchCurrencyListScheduled(){
        getCurrencyListWithBase("USD");
    }

    public void getCurrencyListWithBase(String base){

        String uri = "/latest?base=" + base;

        webClient.get().uri(uri).retrieve().bodyToFlux(CurrencyList.class).subscribe(kafkaPublishSerivce::publish);

    }
}
