package com.ivomtdias.springshopapi.repository;

import com.ivomtdias.springshopapi.model.Order;
import com.ivomtdias.springshopapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findOrdersByUser(User user);
}
