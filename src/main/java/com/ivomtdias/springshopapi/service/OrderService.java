package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDTO createOrder();
    List<OrderDTO> getUserOrders();
    List<OrderDTO> getAllOrders();
    OrderDTO payOrder(UUID orderId);
}
