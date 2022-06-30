package com.example.cloudstorage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BasicConfiguration {

    @Autowired
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
