package com.gabriel_mello.insurance_api.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gabriel_mello.insurance_api.domain.Client;
import com.gabriel_mello.insurance_api.domain.Insurance;
import com.gabriel_mello.insurance_api.domain.InsuranceContract;
import com.gabriel_mello.insurance_api.domain.ports.outbound.ClientClientPort;
import com.gabriel_mello.insurance_api.domain.ports.outbound.InsurancePersistencePort;

public class InsuranceServiceTest {
	@Mock
	private InsurancePersistencePort insurancePersistencePort;

	@Mock
	private ClientClientPort clientClientPort;

	@InjectMocks
	private InsuranceService insuranceService;

	private Client client;
	private Insurance insurance;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		client = new Client();
		client.setId("client1");
		client.setName("John Doe");

		insurance = new Insurance();
		insurance.setType("Gold");
		insurance.setPrice(1000.00);
	}

	@Test
	void simulateInsurance_ShouldReturnInsurance_WhenInsuranceTypeIsProvided() {
		when(insurancePersistencePort.simulateInsurance("Gold")).thenReturn(insurance);

		Insurance result = insuranceService.simulateInsurance("Gold");

		assertNotNull(result);
		assertEquals("Gold", result.getType());
		verify(insurancePersistencePort, times(1)).simulateInsurance("Gold");
	}

	@Test
	void takeoutInsurance_ShouldReturnInsuranceContract_WhenClientExists() {
		when(clientClientPort.getClientById("client1")).thenReturn(Optional.of(client));
		when(insurancePersistencePort.simulateInsurance("Gold")).thenReturn(insurance);
		when(insurancePersistencePort.createInsuranceContract(client, insurance)).thenReturn(
				new InsuranceContract("contract1", client.getId(), insurance.getType(), insurance.getPrice(), LocalDateTime.now()));

		InsuranceContract contract = insuranceService.takeoutInsurance("client1", "Gold");

		assertNotNull(contract);
		assertEquals(client.getId(), contract.getClientId());
		assertEquals("Gold", contract.getInsuranceType());
		verify(clientClientPort, times(1)).getClientById("client1");
		verify(insurancePersistencePort, times(1)).simulateInsurance("Gold");
		verify(insurancePersistencePort, times(1)).createInsuranceContract(client, insurance);
	}

	@Test
	void takeoutInsurance_ShouldReturnNull_WhenClientDoesNotExist() {
		when(clientClientPort.getClientById("client1")).thenReturn(Optional.empty());

		InsuranceContract contract = insuranceService.takeoutInsurance("client1", "Gold");

		assertNull(contract);
		verify(clientClientPort, times(1)).getClientById("client1");
		verify(insurancePersistencePort, never()).simulateInsurance(anyString());
		verify(insurancePersistencePort, never()).createInsuranceContract(any(Client.class), any(Insurance.class));
	}
}
