package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.dto.OrderDTO;
import com.ivomtdias.springshopapi.model.response.OrderReceivedResponse;
import com.ivomtdias.springshopapi.model.response.OrderShippedResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDTO createOrder();
    List<OrderDTO> getUserOrders();
    List<OrderDTO> getAllOrders();
    OrderDTO payOrder(UUID orderId);
    OrderShippedResponse shipOrder(UUID orderId);
    OrderReceivedResponse completeOrder(UUID orderId);
}
