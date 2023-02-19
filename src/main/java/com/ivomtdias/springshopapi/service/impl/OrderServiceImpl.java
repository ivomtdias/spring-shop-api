package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.model.Order;
import com.ivomtdias.springshopapi.model.OrderProduct;
import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.model.dto.OrderDTO;
import com.ivomtdias.springshopapi.model.mapper.OrderDTOMapper;
import com.ivomtdias.springshopapi.repository.OrderProductRepository;
import com.ivomtdias.springshopapi.repository.OrderRepository;
import com.ivomtdias.springshopapi.service.CartService;
import com.ivomtdias.springshopapi.service.OrderService;
import com.ivomtdias.springshopapi.service.UserService;
import com.ivomtdias.springshopapi.statemachine.order.OrderStateMachine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderDTOMapper orderDTOMapper;
    private final CartService cartService;

    private final OrderProductRepository orderProductRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, OrderDTOMapper orderDTOMapper, CartService cartService, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderDTOMapper = orderDTOMapper;
        this.cartService = cartService;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderDTO createOrder() {
        User user = userService.getLoggedInUser();

        Order order = orderRepository.save(Order.builder()
                .orderState(OrderStateMachine.initialState())
                .user(user)
                .build());

        final Order finalOrder = order;

        List<OrderProduct> orderProducts = cartService.getProducts().stream()
                .map(cartProduct -> OrderProduct.builder()
                        .product(cartProduct.getProduct())
                        .order(finalOrder)
                        .build())
                .toList();

        orderProductRepository.saveAll(orderProducts);

        order = orderRepository.findById(order.getId()).get();

        cartService.clearCart();

        return orderDTOMapper.apply(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        User user = userService.getLoggedInUser();
        List<Order> ordersByUser = orderRepository.findOrdersByUser(user);
        return ordersByUser.stream()
                .map(orderDTOMapper)
                .toList();
    }
}
