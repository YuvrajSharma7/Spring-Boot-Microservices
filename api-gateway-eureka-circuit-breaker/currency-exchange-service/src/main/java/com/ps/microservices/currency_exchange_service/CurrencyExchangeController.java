package com.ps.microservices.currency_exchange_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;
    @Autowired
    CurrencyExchangeRepository exchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
//        CurrencyExchange exchange= new CurrencyExchange(1000L,from,to, BigDecimal.valueOf(50));
        CurrencyExchange exchange= exchangeRepository.findByFromAndTo(from,to);
        if(exchange==null) throw new RuntimeException("Unable to find data for "+from+" to "+to);
        String port= environment.getProperty("local.server.port");
        exchange.setEnvironment(port);

        return exchange;
    }
}
