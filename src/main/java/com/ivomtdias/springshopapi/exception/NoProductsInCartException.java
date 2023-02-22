package com.ivomtdias.springshopapi.exception;

public class NoProductsInCartException extends RuntimeException{
    public NoProductsInCartException(){
        super("Can't complete order because there is no products in cart!");
    }
}
