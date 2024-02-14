package org.acme.client.resource;

import java.util.NoSuchElementException;

import org.acme.client.dto.CreateClientDto;
import org.acme.client.dto.UpdateClientDto;
import org.acme.client.entity.ClientEntity;
import org.acme.client.mapper.IClientMapper;
import org.acme.client.service.ICountryService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private IClientMapper clientMapper;

    @Inject
    private ICountryService countryService;

    @Inject
    public ClientResource(IClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @POST
    @Transactional
    public Response create(@Valid CreateClientDto createClientDto) {
        var demonym = countryService.getDemonymByCountry(createClientDto.countryCode());
        var entity = clientMapper.fromCreate(createClientDto, demonym);
        entity.persist();
        return Response.ok(entity).build();
    }

    @GET
    public Response getAll() {
        try {
            return Response.ok(ClientEntity.listAll()).build();
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException("Error del servidor al obtener los datos de los clientes");
        }
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            var client = ClientEntity.findById(id);
            if (client == null) {
                throw new NoSuchElementException("No hay datos del cliente con id: " + id);
            }
            return Response.ok(client).build();
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException("Error del servidor al obtener los detalles de un cliente");
        }
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            var client = ClientEntity.findById(id);
            if (client == null) {
                throw new NoSuchElementException("No fue encontrado el cliente con el siguiente id: " + id);
            }

            ClientEntity.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException("Error del servidor al eliminar un cliente en especifico");
        }
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid UpdateClientDto updateClientDto) {
        var demonym = countryService.getDemonymByCountry(updateClientDto.countryCode());
        var updatedClient = clientMapper.fromUpdate(id, updateClientDto, demonym);
        if (updatedClient == null) {
            throw new NoSuchElementException("El cliente con el id: " + id + " no fue encontrado");
        }

        ClientEntity.persist(updatedClient);
        return Response.ok(updatedClient).build();
    }

    @GET
    @Path("getClientsByDemonyms")
    public Response getClientsByDemonyms(@QueryParam("demonym") String demonym) {
        try {
            if (demonym == null || demonym.isEmpty()) {
                throw new BadRequestException("El parámetro de gentilicio no puede estar vacío");
            }

            var clientListByDemonyms = ClientEntity.list("demonym", demonym);
            return Response.ok(clientListByDemonyms).build();
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(
                    "Error del servidor al obtener los clientes pertenecientes a un país");
        }
    }

    // private String getDemonymByCountry(String countryCode) {
    //     try {
    //         String country = clientService.getCountryByCode(countryCode);
    //         if(country == null){
    //             throw new NoSuchElementException("No fue encontrado el pais con el siguiente codigo: " + countryCode);
    //         }
    //         ObjectMapper mapper = new ObjectMapper();
    //         JsonNode rootNode = mapper.readTree(country);
    //         JsonNode demonymsNode = rootNode.get(0).get("demonyms");
    //         String englishDemomyn = demonymsNode.get("eng").get("f").asText();

    //         return englishDemomyn;
    //     } catch (JsonProcessingException e) {
    //         return null;
    //     }
    // }
}
