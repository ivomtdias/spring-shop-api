package com.ivomtdias.springshopapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderShippedResponse {
    UUID orderId;
    UUID userId;
    String destination;
}
