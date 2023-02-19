package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder();
    List<OrderDTO> getAllOrders();
}
