package com.gabriel_mello.clients_api.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gabriel_mello.clients_api.domain.Client;
import com.gabriel_mello.clients_api.domain.ports.inboud.ClientServicePort;
import com.gabriel_mello.clients_api.domain.ports.outbound.ClientPersistencePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClientService implements ClientServicePort {

	private final ClientPersistencePort clientPersistencePort;

	@Override
	public Client createClient(Client client) {
		return clientPersistencePort.createClient(client);
	}

	@Override
	public Client getClientById(String id) {
		Optional<Client> client = clientPersistencePort.getClientById(id);
		if (client.isPresent()) {
			return client.get();
		}
		return null;
	}

	@Override
	public boolean deleteClient(String id) {
		if (clientPersistencePort.existsClientById(id)) {
			clientPersistencePort.deleteClient(id);
			return true;
		}
		return false;
	}

	@Override
	public Client updateClient(String id, Client client) {
		if (clientPersistencePort.existsClientById(id)) {
			client.setId(id);
			return clientPersistencePort.createClient(client);
		}
		return null;
	}

}
