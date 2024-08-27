package com.gabriel_mello.insurance_api.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "insurance_contracts")
public class InsuranceContract {
	@Id
    private String id; // Identificador único do contrato
    private String clientId; // Referência ao cliente que contratou o seguro
    private String insuranceType; // Tipo do seguro (Bronze, Prata, Ouro)
    private double price; // Preço do seguro
    @CreatedDate
    private LocalDateTime contractDate; // Data de contratação do seguro;
}
