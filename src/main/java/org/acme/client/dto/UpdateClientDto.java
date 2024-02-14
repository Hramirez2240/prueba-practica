package org.acme.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateClientDto(
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
