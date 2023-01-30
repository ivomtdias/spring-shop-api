package com.ivomtdias.springshopapi.exception;

public class IllegalStockException extends RuntimeException{
    public IllegalStockException(){
        super("Stock can't be lower than zero!");
    }
}
