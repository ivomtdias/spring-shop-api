package com.ivomtdias.springshopapi.model.dto;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String name,
        double price
) {
}
