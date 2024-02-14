package org.acme.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateClientDto(
    @NotBlank 
    String firstName,

    String middleName,

    @NotBlank 
    String firstLastName,

    String secondLastName,

    @NotBlank
    @Email 
    String email,

    @NotBlank
    String address,

    @NotBlank
    String phoneNumber,

    @NotBlank
    String countryCode
) {}
