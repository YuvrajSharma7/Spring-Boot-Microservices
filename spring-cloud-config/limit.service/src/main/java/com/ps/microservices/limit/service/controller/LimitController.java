package com.ps.microservices.limit.service.controller;

import com.ps.microservices.limit.service.bean.Limits;
import com.ps.microservices.limit.service.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LimitController {

    @Autowired
    Configuration configuration;
    @GetMapping("/limits")
    public Limits retriveLimits(){
//        return new Limits(1,1000);
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
