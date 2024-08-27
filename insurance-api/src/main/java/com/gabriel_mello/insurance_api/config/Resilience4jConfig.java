package com.gabriel_mello.insurance_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;

@Configuration
public class Resilience4jConfig {
	@Bean
    public Retry retry() {
        return Retry.ofDefaults("clientServiceCB");
    }

    @Bean
    public CircuitBreaker circuitBreaker() {
        return CircuitBreaker.ofDefaults("clientServiceCB");
    }
}
