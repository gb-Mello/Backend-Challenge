package com.gabriel_mello.insurance_api.infraestructure.adapter;

import org.springframework.stereotype.Component;

import com.gabriel_mello.insurance_api.domain.Client;
import com.gabriel_mello.insurance_api.domain.Insurance;
import com.gabriel_mello.insurance_api.domain.InsuranceContract;
import com.gabriel_mello.insurance_api.domain.ports.outbound.InsurancePersistencePort;
import com.gabriel_mello.insurance_api.infraestructure.repository.InsuranceContractRepository;
import com.gabriel_mello.insurance_api.infraestructure.repository.InsuranceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class InsurancePersistenceAdapter implements InsurancePersistencePort{
	
	private final InsuranceRepository insuranceRepository;
	private final InsuranceContractRepository insuranceContractRepository;

	@Override
	public Insurance simulateInsurance(String insuranceType) {
		return insuranceRepository.findByType(insuranceType);
	}

	@Override
	public InsuranceContract createInsuranceContract(Client client, Insurance insurance) {
		return insuranceContractRepository.save(new InsuranceContract(null, client.getId(), insurance.getType(), insurance.getPrice(), null));
	}

}
