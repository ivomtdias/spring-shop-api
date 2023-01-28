package com.ivomtdias.springshopapi.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(UUID id){
        super(String.format("Product with id: %s not found!", id));
    }
    public ProductNotFoundException(String name){
        super(String.format("Product with name: %s not found!", name));
    }
}
