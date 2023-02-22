package com.ivomtdias.springshopapi.exception;

import com.ivomtdias.springshopapi.model.Stock;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(Stock stock, int quantity){
        super(String.format("The product: %s only have %s in stock and you requested %s", stock.getProduct().getId(), stock.getQuantity(), quantity));
    }
}
