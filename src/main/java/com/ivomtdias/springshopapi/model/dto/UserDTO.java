package com.ivomtdias.springshopapi.model.dto;

import com.ivomtdias.springshopapi.model.Role;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Role role,
        String address,
        String zipCode,
        String country
) {
}
