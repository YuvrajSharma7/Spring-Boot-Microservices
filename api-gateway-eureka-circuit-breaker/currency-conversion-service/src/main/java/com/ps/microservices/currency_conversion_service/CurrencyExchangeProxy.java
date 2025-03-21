package com.ps.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// value is the name of the target spring boot application
// url is at which the target service is running
//@FeignClient(value = "currency-exchange",url = "localhost:8000")

// if there are multiple instances of target service are running so to implement load balancing remove url attribute as below
@FeignClient(value = "currency-exchange")
public interface CurrencyExchangeProxy {

    // we just write the method signature as in the target service and its implementation is provided at run time
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

    }
