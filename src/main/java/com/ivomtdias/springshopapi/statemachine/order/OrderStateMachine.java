package com.ivomtdias.springshopapi.statemachine.order;

public class OrderStateMachine {
    private OrderStateMachine(){
        throw new IllegalStateException("Utility class");
    }

    public static OrderState getNextState(OrderState currentState) {
        return switch (currentState) {
            case PENDING_PAYMENT -> OrderState.PENDING_SHIPMENT;
            case PENDING_SHIPMENT -> OrderState.SHIPPED;
            case SHIPPED -> OrderState.COMPLETED;
            default -> currentState;
        };
    }

    public static OrderState cancelOrder(){
        return OrderState.CANCELED;
    }

    public static OrderState initialState() {
        return OrderState.PENDING_PAYMENT;
    }
}
