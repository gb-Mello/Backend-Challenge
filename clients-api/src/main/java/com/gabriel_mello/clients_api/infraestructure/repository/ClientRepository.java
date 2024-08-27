package com.gabriel_mello.clients_api.infraestructure.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gabriel_mello.clients_api.domain.Client;

public interface ClientRepository extends MongoRepository<Client, String>{

}
