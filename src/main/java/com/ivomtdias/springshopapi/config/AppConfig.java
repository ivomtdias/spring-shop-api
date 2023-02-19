package com.ivomtdias.springshopapi.config;

import com.ivomtdias.springshopapi.statemachine.order.OrderStateMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public OrderStateMachine orderStateMachine() {
        return new OrderStateMachine();
    }
}
