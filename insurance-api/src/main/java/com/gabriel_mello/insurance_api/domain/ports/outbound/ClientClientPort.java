package com.gabriel_mello.insurance_api.domain.ports.outbound;

import java.util.Optional;

import com.gabriel_mello.insurance_api.domain.Client;

public interface ClientClientPort {
	Optional<Client> getClientById(String id);
}
