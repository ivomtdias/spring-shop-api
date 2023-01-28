package com.ivomtdias.springshopapi.model.mapper;

import com.ivomtdias.springshopapi.model.Product;
import com.ivomtdias.springshopapi.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
