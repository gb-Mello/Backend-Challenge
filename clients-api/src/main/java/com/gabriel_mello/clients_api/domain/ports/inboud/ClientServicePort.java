package com.gabriel_mello.clients_api.domain.ports.inboud;

import com.gabriel_mello.clients_api.domain.Client;

public interface ClientServicePort {
	Client createClient(Client client);

	Client getClientById(String id);

	boolean deleteClient(String id);

	Client updateClient(String id, Client client);
}
