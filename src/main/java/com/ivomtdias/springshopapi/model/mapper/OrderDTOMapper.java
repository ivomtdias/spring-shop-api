package com.ivomtdias.springshopapi.model.mapper;

import com.ivomtdias.springshopapi.model.Order;
import com.ivomtdias.springshopapi.model.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    private final ProductDTOMapper productDTOMapper;

    public OrderDTOMapper(ProductDTOMapper productDTOMapper) {
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getUser().getId(),
                order.getProductList().stream().map(product -> productDTOMapper.apply(product.getProduct())).toList(),
                order.getProductList().stream().mapToDouble(product -> product.getProduct().getPrice()).sum(),
                order.getOrderState()
        );
    }
}
