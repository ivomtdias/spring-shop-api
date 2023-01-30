package com.ivomtdias.springshopapi.exception;

import java.util.UUID;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(UUID productId){
        super(String.format("No stock information found for product with id: %s", productId));
    }
}
