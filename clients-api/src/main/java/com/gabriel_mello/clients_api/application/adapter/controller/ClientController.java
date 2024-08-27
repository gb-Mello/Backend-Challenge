package com.gabriel_mello.clients_api.application.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel_mello.clients_api.domain.Client;
import com.gabriel_mello.clients_api.domain.ports.inboud.ClientServicePort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
@Tag(name = "Client", description = "API for customer management")
public class ClientController {

	private final ClientServicePort clientServicePort;

	@Operation(summary = "Create a new client")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client created successfully", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Client.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		return ResponseEntity.ok(clientServicePort.createClient(client));
	}

	@Operation(summary = "Get client by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client found", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Client.class))),
        @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable String id) {
		Client client = clientServicePort.getClientById(id);
		if (client != null) {
			return ResponseEntity.ok(client);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Delete client by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Client deleted successfully", content = @Content),
        @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable String id) {
		boolean deleted = clientServicePort.deleteClient(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Update client by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Client updated successfully", 
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Client.class))),
        @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody Client clientUpdated) {
		Client client = clientServicePort.updateClient(id, clientUpdated);

		if (client != null) {
			return ResponseEntity.ok(client);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
