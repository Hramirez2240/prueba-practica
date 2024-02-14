package org.acme.client.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ICountryService {

    public String getDemonymByCountry(String countryCode);
}