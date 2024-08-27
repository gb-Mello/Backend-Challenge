package com.gabriel_mello.insurance_api.infraestructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gabriel_mello.insurance_api.domain.Insurance;

public interface InsuranceRepository extends MongoRepository<Insurance, String> {
	
	Insurance findByType(String insuranceType);
	
}
