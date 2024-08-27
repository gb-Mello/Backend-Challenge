package com.gabriel_mello.insurance_api.domain.ports.outbound;

import com.gabriel_mello.insurance_api.domain.Client;
import com.gabriel_mello.insurance_api.domain.Insurance;
import com.gabriel_mello.insurance_api.domain.InsuranceContract;

public interface InsurancePersistencePort {
	Insurance simulateInsurance(String insuranceType);
	InsuranceContract createInsuranceContract(Client client,Insurance insurance);
}
