package com.ivomtdias.springshopapi.repository;

import com.ivomtdias.springshopapi.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
