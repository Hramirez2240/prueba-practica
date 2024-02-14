package org.acme.client.mapper;

import org.acme.client.dto.CreateClientDto;
import org.acme.client.dto.UpdateClientDto;
import org.acme.client.entity.ClientEntity;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ClientMapperImpl implements IClientMapper {

    @Override
    public ClientEntity fromCreate(CreateClientDto createClientDto, String demonym) {
        var clientEntity = new ClientEntity();
        clientEntity.setFirstName(createClientDto.firstName());
        clientEntity.setMiddleName(createClientDto.middleName());
        clientEntity.setFirstLastName(createClientDto.firstLastName());
        clientEntity.setSecondLastName(createClientDto.secondLastName());
        clientEntity.setEmail(createClientDto.email());
        clientEntity.setAddress(createClientDto.address());
        clientEntity.setPhoneNumber(createClientDto.phoneNumber());
        clientEntity.setCountryCode(createClientDto.countryCode());
        clientEntity.setDemonym(demonym);

        return clientEntity;
    }

    @Override
    public ClientEntity fromUpdate(Long id, UpdateClientDto updateClientDto, String demonym) {
        var panacheEntity = ClientEntity.findById(id);
        var client = ClientEntity.class.cast(panacheEntity);
        if(client == null) {
            return null;
        }
        
        client.setEmail(updateClientDto.email());
        client.setAddress(updateClientDto.address());
        client.setPhoneNumber(updateClientDto.phoneNumber());
        client.setCountryCode(updateClientDto.countryCode());
        client.setDemonym(demonym);

        return client;
    }
    
}
