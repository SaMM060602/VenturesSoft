package com.sandy.empleados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    // Exposes a singleton WebClient bean for dependency injection
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}