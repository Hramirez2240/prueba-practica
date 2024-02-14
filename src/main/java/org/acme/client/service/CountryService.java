package org.acme.client.service;

import java.util.NoSuchElementException;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CountryService implements ICountryService {
    @Inject
    @RestClient
    IClientService clientService;

    @Override
    public String getDemonymByCountry(String countryCode) {
        try {
            String country = clientService.getCountryByCode(countryCode);
            if(country == null){
                throw new NoSuchElementException("No fue encontrado el pais con el siguiente codigo: " + countryCode);
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(country);
            JsonNode demonymsNode = rootNode.get(0).get("demonyms");
            String englishDemomyn = demonymsNode.get("eng").get("f").asText();

            return englishDemomyn;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    
}
