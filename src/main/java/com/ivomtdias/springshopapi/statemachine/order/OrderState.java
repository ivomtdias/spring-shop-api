package com.ivomtdias.springshopapi.statemachine.order;

public enum OrderState {
    PENDING_PAYMENT, PENDING_SHIPMENT, SHIPPED, COMPLETED, CANCELED
}
