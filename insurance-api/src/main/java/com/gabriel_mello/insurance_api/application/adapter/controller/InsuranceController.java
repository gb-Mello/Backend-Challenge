package com.gabriel_mello.insurance_api.application.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel_mello.insurance_api.domain.Insurance;
import com.gabriel_mello.insurance_api.domain.InsuranceContract;
import com.gabriel_mello.insurance_api.domain.ports.inbound.InsuranceServicePort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/insurances")
@AllArgsConstructor
@Tag(name = "Insurance", description = "API for insurance management")
public class InsuranceController {
	private final InsuranceServicePort servicePort;
	
	@Operation(summary = "Simulate insurance based on the type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Insurance simulated successfully", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Insurance.class))),
        @ApiResponse(responseCode = "404", description = "Insurance type not found", content = @Content)
    })
	@GetMapping("/simulate")
	public ResponseEntity<Insurance> simulateInsurance(@RequestParam String insuranceType) {
		Insurance insurance = servicePort.simulateInsurance(insuranceType);
		if (insurance != null) {
			return ResponseEntity.ok(insurance);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Take out an insurance for a client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Insurance contracted successfully", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = InsuranceContract.class))),
        @ApiResponse(responseCode = "404", description = "Client or insurance type not found", content = @Content)
    })
	@PostMapping("/takeout")
	public ResponseEntity<InsuranceContract> takeoutInsurance(@RequestParam String clientId, @RequestParam String insuranceType) {
		InsuranceContract insurancecontract = servicePort.takeoutInsurance(clientId, insuranceType);
		if (insurancecontract != null) {
			return ResponseEntity.ok(insurancecontract); // Retorna 200 OK com o cliente atualizado
		} else {
			return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o cliente não for encontrado
		}
    }
}
