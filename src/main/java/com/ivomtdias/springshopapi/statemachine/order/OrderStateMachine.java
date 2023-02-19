package com.ivomtdias.springshopapi.statemachine.order;

public class OrderStateMachine {
    public static OrderState getNextState(OrderState currentState) {
        return switch (currentState) {
            case PENDING -> OrderState.PENDING_PAYMENT;
            case PENDING_PAYMENT -> OrderState.PENDING_SHIPMENT;
            case PENDING_SHIPMENT -> OrderState.SHIPPED;
            case SHIPPED -> OrderState.COMPLETED;
            default -> currentState;
        };
    }

    public static OrderState cancelOrder(){
        return OrderState.CANCELLED;
    }

    public static OrderState initialState() {
        return OrderState.PENDING;
    }
}
