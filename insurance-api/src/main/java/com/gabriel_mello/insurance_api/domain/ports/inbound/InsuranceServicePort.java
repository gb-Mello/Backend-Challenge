package com.gabriel_mello.insurance_api.domain.ports.inbound;

import com.gabriel_mello.insurance_api.domain.Insurance;
import com.gabriel_mello.insurance_api.domain.InsuranceContract;

public interface InsuranceServicePort {
	Insurance simulateInsurance(String insuranceType);

	InsuranceContract takeoutInsurance(String clientId, String insuranceType);
}
