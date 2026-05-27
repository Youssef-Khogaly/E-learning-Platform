package com.elearning.External.VideoExternalService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import video.api.client.ApiVideoClient;
import video.api.client.api.models.Environment;

@Configuration
public class ApiVideoConfig {


    @Bean
    public ApiVideoClient apiVideoClient(@Value("${api.video.test.apiKey}") String apiKey , @Value("${api.video.enviroment}")String environment){
        Environment envEnum;
        if(environment.equalsIgnoreCase("SANDBOX"))
            envEnum = Environment.SANDBOX;
        else if(environment.equalsIgnoreCase("PRODUCTION"))
            envEnum = Environment.PRODUCTION;
        else
            throw new IllegalArgumentException("Invalid api.video env , provided" + environment + " allowed: SANDBOX , PRODUCTION" );

        return new ApiVideoClient(apiKey, envEnum);
    }
}
