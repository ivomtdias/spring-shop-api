package com.ivomtdias.springshopapi.repository;

import com.ivomtdias.springshopapi.model.Order;
import com.ivomtdias.springshopapi.model.User;
import com.ivomtdias.springshopapi.statemachine.order.OrderState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByUser(User user);

    @Query(value = "select o from Order o where " +
            "o.id = :orderId and " +
            "o.user.id = :userId and " +
            "o.orderState = 'PENDING_PAYMENT'")
    Optional<Order> findOrderByIdAndUserAndPendingPaymentState(
            @Param("orderId") UUID orderId,
            @Param("userId") UUID userId
    );

    @Modifying
    @Transactional
    @Query("update Order o set o.orderState = :orderState where o.id = :orderId and o.user.id = :userId")
    void updateOrderState(
            @Param("orderId") UUID orderId,
            @Param("userId") UUID userId,
            @Param("orderState") OrderState orderState
    );

    @Modifying
    @Transactional
    @Query("update Order o set o.orderState = :orderState where o.id = :orderId")
    void updateOrderState(
            @Param("orderId") UUID orderId,
            @Param("orderState") OrderState orderState
    );
}
