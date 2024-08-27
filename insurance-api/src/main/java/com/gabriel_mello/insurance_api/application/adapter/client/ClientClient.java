package com.gabriel_mello.insurance_api.application.adapter.client;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.gabriel_mello.insurance_api.domain.Client;
import com.gabriel_mello.insurance_api.domain.ports.outbound.ClientClientPort;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClientClient implements ClientClientPort {
	private final RestTemplate restTemplate;
	private final String clientApiUrl = "http://localhost:8080/api/clients";

	@CircuitBreaker(name = "clientServiceCircuitBreaker", fallbackMethod = "fallbackGetClientById")
    @Retry(name = "clientServiceRetry")
	@Override
	public Optional<Client> getClientById(String id) {
		try {
			ResponseEntity<Client> response = restTemplate.getForEntity(clientApiUrl + "/" + id, Client.class);
			return Optional.ofNullable(response.getBody());
		} catch (HttpClientErrorException e) {
			return Optional.empty();
		}
	}
	
	public Optional<Client> fallbackGetClientById(String id, Throwable throwable) {
        System.out.println("Fallback called for clientId: " + id + " due to: " + throwable.getMessage());
        return Optional.empty();
    }

}
