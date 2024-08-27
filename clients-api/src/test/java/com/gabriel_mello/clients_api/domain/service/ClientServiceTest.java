package com.gabriel_mello.clients_api.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gabriel_mello.clients_api.domain.Client;
import com.gabriel_mello.clients_api.domain.ports.outbound.ClientPersistencePort;

public class ClientServiceTest {
	@Mock
    private ClientPersistencePort clientPersistencePort;

    @InjectMocks
    private ClientService clientService;
    
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId("1");
        client.setName("Gabriel Mello");
    }

    @Test
    void createClient_ShouldReturnCreatedClient() {
        when(clientPersistencePort.createClient(client)).thenReturn(client);

        Client createdClient = clientService.createClient(client);

        assertNotNull(createdClient);
        assertEquals("1", createdClient.getId());
        verify(clientPersistencePort, times(1)).createClient(client);
    }

    @Test
    void getClientById_ShouldReturnClient_WhenClientExists() {
        String clientId = "1";
        when(clientPersistencePort.getClientById(clientId)).thenReturn(Optional.of(client));

        Client foundClient = clientService.getClientById(clientId);

        assertNotNull(foundClient);
        assertEquals(clientId, foundClient.getId());
        verify(clientPersistencePort, times(1)).getClientById(clientId);
    }

    @Test
    void getClientById_ShouldReturnNull_WhenClientDoesNotExist() {
        String clientId = "1";
        when(clientPersistencePort.getClientById(clientId)).thenReturn(Optional.empty());

        Client foundClient = clientService.getClientById(clientId);

        assertNull(foundClient);
        verify(clientPersistencePort, times(1)).getClientById(clientId);
    }

    @Test
    void deleteClient_ShouldReturnTrue_WhenClientExists() {
        String clientId = "1";
        when(clientPersistencePort.existsClientById(clientId)).thenReturn(true);

        boolean result = clientService.deleteClient(clientId);

        assertTrue(result);
        verify(clientPersistencePort, times(1)).deleteClient(clientId);
    }

    @Test
    void deleteClient_ShouldReturnFalse_WhenClientDoesNotExist() {
        String clientId = "1";
        when(clientPersistencePort.existsClientById(clientId)).thenReturn(false);

        boolean result = clientService.deleteClient(clientId);

        assertFalse(result);
        verify(clientPersistencePort, never()).deleteClient(clientId);
    }

    @Test
    void updateClient_ShouldReturnUpdatedClient_WhenClientExists() {
        String clientId = "1";
        when(clientPersistencePort.existsClientById(clientId)).thenReturn(true);
        when(clientPersistencePort.createClient(client)).thenReturn(client);

        Client updatedClient = clientService.updateClient(clientId, client);

        assertNotNull(updatedClient);
        assertEquals(clientId, updatedClient.getId());
        verify(clientPersistencePort, times(1)).createClient(client);
    }

    @Test
    void updateClient_ShouldReturnNull_WhenClientDoesNotExist() {
        String clientId = "1";
        when(clientPersistencePort.existsClientById(clientId)).thenReturn(false);

        Client updatedClient = clientService.updateClient(clientId, client);

        assertNull(updatedClient);
        verify(clientPersistencePort, never()).createClient(client);
    }
}
