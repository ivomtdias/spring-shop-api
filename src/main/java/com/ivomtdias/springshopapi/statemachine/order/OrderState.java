package com.ivomtdias.springshopapi.statemachine.order;

public enum OrderState {
    PENDING, PENDING_PAYMENT, PENDING_SHIPMENT, SHIPPED, COMPLETED, CANCELLED
}