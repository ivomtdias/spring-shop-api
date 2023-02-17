package com.ivomtdias.springshopapi.exception;

import java.util.UUID;

public class ProductNotInCartException extends RuntimeException{
    public ProductNotInCartException(UUID cartId, UUID productId){
        super(String.format("Product with Id: %s does not exist in Cart: %s", cartId, productId));
    }
}
