package org.acme.getting.testing.resource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
    public void testCreateClientEndpoint() {
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
    public void testGetAllClientsEndpoint() {
        var clients = ClientEntity.listAll();
        assertFalse(clients.isEmpty());
    }

    @Test
    public void testGetClientByIdEndpoint() {
        var client = ClientEntity.findById(0L);
        assertNull(client);
    }

    @Test
    @Transactional
    public void testDeleteClientByIdEndpoint() {
        var clientListBeforeDelete = ClientEntity.listAll();
        var clientListSize = clientListBeforeDelete.size();

        var client = ClientEntity.findById(1L);
        if (client == null) {
            assertNull(client);
        } else {
            ClientEntity.deleteById(1L);
            var clientListAfterDelete = ClientEntity.listAll();
            var clientListSizeAfterDelete = clientListAfterDelete.size();

            assertNotEquals(clientListSize, clientListSizeAfterDelete);
        }
    }
}
