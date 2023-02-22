package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.exception.NoProductsInCartException;
import com.ivomtdias.springshopapi.exception.NotEnoughStockException;
import com.ivomtdias.springshopapi.model.Order;
import com.ivomtdias.springshopapi.model.OrderProduct;
import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.model.dto.OrderDTO;
import com.ivomtdias.springshopapi.model.mapper.OrderDTOMapper;
import com.ivomtdias.springshopapi.repository.OrderProductRepository;
import com.ivomtdias.springshopapi.repository.OrderRepository;
import com.ivomtdias.springshopapi.service.CartService;
import com.ivomtdias.springshopapi.service.OrderService;
import com.ivomtdias.springshopapi.service.StockService;
import com.ivomtdias.springshopapi.service.UserService;
import com.ivomtdias.springshopapi.statemachine.order.OrderStateMachine;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderDTOMapper orderDTOMapper;
    private final CartService cartService;
    private final OrderProductRepository orderProductRepository;
    private final StockService stockService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, OrderDTOMapper orderDTOMapper, CartService cartService, OrderProductRepository orderProductRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderDTOMapper = orderDTOMapper;
        this.cartService = cartService;
        this.orderProductRepository = orderProductRepository;
        this.stockService = stockService;
    }

    @Override
    public OrderDTO createOrder() {
        User user = userService.getLoggedInUser();

        if(cartService.getProducts().isEmpty())
            throw new NoProductsInCartException();

        final Order order = orderRepository.save(Order.builder()
                .orderState(OrderStateMachine.initialState())
                .user(user)
                .build());

        List<OrderProduct> orderProducts = cartService.getProducts().stream()
                .map(cartProduct -> OrderProduct.builder()
                        .product(cartProduct.getProduct())
                        .order(order)
                        .build())
                .toList();

        Map<UUID, Integer> productCounter = extractProductCounter(orderProducts);
        productCounter.forEach((productId, quantity) -> {
            if(!stockService.checkIfItIsInStock(productId, quantity))
                throw new NotEnoughStockException(stockService.getStockStatus(productId), quantity);
        });

        productCounter.forEach(stockService::removeStock);
        orderProductRepository.saveAll(orderProducts);
        cartService.clearCart();

        return orderDTOMapper.apply(orderRepository.findById(order.getId()).get());
    }

    private Map<UUID, Integer> extractProductCounter(List<OrderProduct> orderProducts) {
        Map<UUID, Integer> productCounter = new HashMap<>();
        for(OrderProduct orderProduct : orderProducts){
            if(productCounter.containsKey(orderProduct.getProduct().getId()))
                productCounter.put(orderProduct.getProduct().getId(), productCounter.get(orderProduct.getProduct().getId()) + 1);
            else
                productCounter.put(orderProduct.getProduct().getId(), 1);
        }
        return productCounter;
    }

    @Override
    public List<OrderDTO> getUserOrders() {
        User user = userService.getLoggedInUser();
        List<Order> ordersByUser = orderRepository.findOrdersByUser(user);
        return ordersByUser.stream()
                .map(orderDTOMapper)
                .toList();
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderDTOMapper).toList();
    }
}
