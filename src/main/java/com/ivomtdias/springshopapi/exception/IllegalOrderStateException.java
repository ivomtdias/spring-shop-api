package com.ivomtdias.springshopapi.exception;

import com.ivomtdias.springshopapi.statemachine.order.OrderState;

import java.util.UUID;

public class IllegalOrderStateException extends RuntimeException{
    public IllegalOrderStateException(UUID orderId, OrderState orderState){
        super(String.format("Order: %s cannot be shipped because it's in %s state!", orderId, orderState));
    }
}
