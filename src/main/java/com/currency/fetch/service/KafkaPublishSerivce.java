package com.currency.fetch.service;


import com.currency.fetch.dto.CurrencyList;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KafkaPublishSerivce {

    private final KafkaTemplate<String, CurrencyList> kafkaTemplate;

    public void publish(CurrencyList rates){
        kafkaTemplate.send("currency-rates",rates);
        System.out.println("Published to Kafka: " + rates);
    }

}
