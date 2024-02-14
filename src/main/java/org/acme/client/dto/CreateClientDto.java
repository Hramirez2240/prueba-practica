package org.acme.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateClientDto(
    @NotBlank
    @Size(max = 20)
    String firstName,

    @Size(max = 20)
    String middleName,

    @NotBlank
    @Size(max = 20)
    String firstLastName,

    @Size(max = 20)
    String secondLastName,

    @NotBlank
    @Email
    @Size(max = 50)
    String email,

    @NotBlank
    @Size(max = 50)
    String address,

    @NotBlank
    @Size(max = 10)
    String phoneNumber,

    @NotBlank
    @Size(max = 10)
    String countryCode
) {}
