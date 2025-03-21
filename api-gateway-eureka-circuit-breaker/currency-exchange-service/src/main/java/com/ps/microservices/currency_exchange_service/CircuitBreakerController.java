package com.ps.microservices.currency_exchange_service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
//    @Retry(name = "default")//default retry is 3 time
    @Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")//to configure custom retry attempts also add corresponding property as in
    // application.properties:resilience4j.retry.instances.sample-api.maxRetryAttempts=5
    @CircuitBreaker(name = "default",fallbackMethod = "hardCodedResponse")//to enable CB open/close mechanism also add corresponding property as in
    // resilience4j.circuitbreaker.instances.default.failureRateThreshold=90
    @RateLimiter(name = "default") // to limit API call in a specific duration
    @Bulkhead(name = "default")// to limit no. of concurrent request allowed at this api also add corresponding property as in
    // resilience4j.circuitbreaker.instances.default.failureRateThreshold=90
    public String sampleApi(){
        logger.info("Sample API call received");
        // to simulate error
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("localhost:8080/dummy-uri", String.class);
        return forEntity.getBody();

//        return "sample-api";
    }
// fallbackMethod called after all retry are unsuccessful, we can have multiple overloaded fall-back methods for
// different type of exceptions to return corresponding response
    public String hardCodedResponse(Exception ex){
        return "fallback-response";
    }
}
