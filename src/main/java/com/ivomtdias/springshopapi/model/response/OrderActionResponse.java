package com.ivomtdias.springshopapi.model.response;

import com.ivomtdias.springshopapi.statemachine.order.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderActionResponse {
    UUID orderId;
    UUID userId;
    String destination;
    OrderState orderState;
}
