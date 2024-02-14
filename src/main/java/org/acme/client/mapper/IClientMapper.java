package org.acme.client.mapper;

import org.acme.client.dto.CreateClientDto;
import org.acme.client.dto.UpdateClientDto;
import org.acme.client.entity.ClientEntity;

public interface IClientMapper {
    ClientEntity fromCreate(CreateClientDto createClientDto, String demonym);
    ClientEntity fromUpdate(Long id, UpdateClientDto updateClientDto, String demonym);
}
