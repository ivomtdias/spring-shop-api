package com.ivomtdias.springshopapi.model.dto;

import java.util.UUID;

public record CartProductDTO(
        UUID id,
        ProductDTO product
) {
}
