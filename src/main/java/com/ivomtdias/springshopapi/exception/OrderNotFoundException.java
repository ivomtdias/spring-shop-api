package com.ivomtdias.springshopapi.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(UUID userId){
        super(String.format("Oder for User: %s not found!", userId));
    }
}
