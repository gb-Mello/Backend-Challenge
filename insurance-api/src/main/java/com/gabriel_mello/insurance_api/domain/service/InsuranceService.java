package com.gabriel_mello.insurance_api.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gabriel_mello.insurance_api.domain.Client;
import com.gabriel_mello.insurance_api.domain.Insurance;
import com.gabriel_mello.insurance_api.domain.InsuranceContract;
import com.gabriel_mello.insurance_api.domain.ports.inbound.InsuranceServicePort;
import com.gabriel_mello.insurance_api.domain.ports.outbound.ClientClientPort;
import com.gabriel_mello.insurance_api.domain.ports.outbound.InsurancePersistencePort;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InsuranceService implements InsuranceServicePort {

	private final InsurancePersistencePort insurancePersistencePort;
	private final ClientClientPort clientClientPort;

	@Override
	public Insurance simulateInsurance(String insuranceType) {
		return insurancePersistencePort.simulateInsurance(insuranceType);
	}

	@Override
	public InsuranceContract takeoutInsurance(String clientId, String insuranceType) {
		Optional<Client> clienteOpt = clientClientPort.getClientById(clientId);
		if (clienteOpt.isPresent()) {
			Insurance insurance = simulateInsurance(insuranceType);
			return insurancePersistencePort.createInsuranceContract(clienteOpt.get(), insurance);
		}
		return null;
	}

}
