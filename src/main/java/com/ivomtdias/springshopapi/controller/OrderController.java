package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.model.dto.OrderDTO;
import com.ivomtdias.springshopapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(){
        return ResponseEntity.ok(orderService.createOrder());
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getUserOrders(){
        return ResponseEntity.ok(orderService.getUserOrders());
    }

    @Secured({"ADMIN"})
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

}
