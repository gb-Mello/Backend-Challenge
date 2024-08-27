package com.gabriel_mello.clients_api.domain.ports.outbound;

import java.util.Optional;

import com.gabriel_mello.clients_api.domain.Client;

public interface ClientPersistencePort {
	Client createClient(Client client);
	Optional<Client> getClientById(String id);
	boolean existsClientById(String id);
	void deleteClient(String id);
}
