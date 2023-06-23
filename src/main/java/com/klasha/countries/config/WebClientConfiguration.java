package com.klasha.countries.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${countries.api.url}")
    private String baseUrl;
    @Bean
    public WebClient webclient() {
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }
}
