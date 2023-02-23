package com.ivomtdias.springshopapi.model.dto;

import com.ivomtdias.springshopapi.statemachine.order.OrderState;

import java.util.List;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        UUID userId,
        List<ProductDTO> products,
        double totalPrice,
        OrderState orderState
) {
}
