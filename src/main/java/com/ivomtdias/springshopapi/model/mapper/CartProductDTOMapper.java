package com.ivomtdias.springshopapi.model.mapper;

import com.ivomtdias.springshopapi.model.CartProduct;
import com.ivomtdias.springshopapi.model.dto.CartProductDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CartProductDTOMapper implements Function<CartProduct, CartProductDTO> {

    private final ProductDTOMapper productDTOMapper;

    public CartProductDTOMapper(ProductDTOMapper productDTOMapper) {
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public CartProductDTO apply(CartProduct cartProduct) {
        return new CartProductDTO(
                cartProduct.getId(),
                productDTOMapper.apply(cartProduct.getProduct())
        );
    }
}
