package com.currency.fetch.service;


import com.currency.fetch.dto.CurrencyList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CurrencyFetchService {

    private final WebClient webClient = WebClient.create("https://api.frankfurter.dev/v1");


    public Mono<CurrencyList> getCurrencyListWithBase(String base){

        Mono<ResponseEntity<CurrencyList>> response = webClient.get().uri("/latest?base=USD").retrieve().toEntity(
                CurrencyList.class
        );

        return response.map(ResponseEntity::getBody);
        
    }
}
