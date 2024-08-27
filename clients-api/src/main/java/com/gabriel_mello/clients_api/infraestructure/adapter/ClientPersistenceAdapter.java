package com.gabriel_mello.clients_api.infraestructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gabriel_mello.clients_api.domain.Client;
import com.gabriel_mello.clients_api.domain.ports.outbound.ClientPersistencePort;
import com.gabriel_mello.clients_api.infraestructure.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClientPersistenceAdapter implements ClientPersistencePort{
	
	private final ClientRepository clientRepository;
	
	@Override
	public Client createClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Optional<Client> getClientById(String id) {
		return clientRepository.findById(id);
	}

	@Override
	public void deleteClient(String id) {
		clientRepository.deleteById(id);
	}

	@Override
	public boolean existsClientById(String id) {
		return clientRepository.existsById(id);
	}
	
}
