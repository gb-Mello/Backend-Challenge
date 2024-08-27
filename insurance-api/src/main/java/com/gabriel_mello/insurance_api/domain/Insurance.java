package com.gabriel_mello.insurance_api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "insurances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {
	@Id
	private String id;
	private String type; // Tipo do seguro (Bronze, Prata, Ouro)
	private double price;
}
