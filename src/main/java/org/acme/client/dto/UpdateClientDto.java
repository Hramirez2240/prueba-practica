package org.acme.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateClientDto(
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
