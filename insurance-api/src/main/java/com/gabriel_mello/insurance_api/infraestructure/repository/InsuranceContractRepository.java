package com.gabriel_mello.insurance_api.infraestructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gabriel_mello.insurance_api.domain.InsuranceContract;

public interface InsuranceContractRepository extends MongoRepository<InsuranceContract, String> {

}
