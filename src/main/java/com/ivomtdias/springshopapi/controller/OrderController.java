package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.model.dto.OrderDTO;
import com.ivomtdias.springshopapi.model.response.OrderActionResponse;
import com.ivomtdias.springshopapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @Secured({"ADMIN", "CUSTOMER"})
    @PostMapping("/{orderId}/pay")
    public ResponseEntity<OrderDTO> payOrder(@PathVariable("orderId") UUID orderId){
        return ResponseEntity.ok(orderService.payOrder(orderId));
    }

    @Secured({"ADMIN"})
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Secured({"ADMIN"})
    @PostMapping("/{orderId}/ship")
    public ResponseEntity<OrderActionResponse> shipOrder(@PathVariable("orderId") UUID orderId){
        return ResponseEntity.ok(orderService.shipOrder(orderId));
    }

    @Secured({"ADMIN"})
    @PostMapping("/{orderId}/complete")
    public ResponseEntity<OrderActionResponse> completeOrder(@PathVariable("orderId") UUID orderId){
        return ResponseEntity.ok(orderService.completeOrder(orderId));
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderActionResponse> cancelOrder(@PathVariable("orderId") UUID orderId){
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }
}
