package org.acme.getting.testing;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

import org.acme.client.dto.CreateClientDto;
import org.acme.client.entity.ClientEntity;
import org.acme.client.mapper.IClientMapper;
import org.acme.client.service.ICountryService;

@QuarkusTest
public class ClientResourceTest {
    @Inject
    IClientMapper clientMapper;

    @Inject
    ICountryService countryService;

    @Test
    public void testCreateClientEndpoint(){
        var clientCreateDto = new CreateClientDto(
            "John", 
            "Doe", 
            "Martinez", 
            "Rodriguez", 
            "hector.ramirez@gmail.com", 
            "Invivienda", 
            "8092347890", 
            "dom");

        var demonym = countryService.getDemonymByCountry(clientCreateDto.countryCode());

        var entity = clientMapper.fromCreate(clientCreateDto, demonym);

        assertNotEquals(entity, clientCreateDto);
        assertEquals("Dominican", demonym);
        
    }

    @Test
    public void testGetAllClientsEndpoint(){
        var clients = ClientEntity.listAll();
        assertFalse(clients.isEmpty());
    }

    @Test
    public void testGetClientByIdEndpoint(){
        var client = ClientEntity.findById(51L);
        assertNotNull(client);
    }
}
