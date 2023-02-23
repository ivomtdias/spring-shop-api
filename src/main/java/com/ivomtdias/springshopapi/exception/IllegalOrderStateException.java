package com.ivomtdias.springshopapi.exception;

import com.ivomtdias.springshopapi.statemachine.order.OrderState;

import java.util.UUID;

public class IllegalOrderStateException extends RuntimeException{
    public IllegalOrderStateException(UUID orderId, OrderState orderState){
        super(String.format("Cannot complete action because Order: %s it's in %s state!", orderId, orderState));
    }
}
