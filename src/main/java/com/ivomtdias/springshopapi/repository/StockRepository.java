package com.ivomtdias.springshopapi.repository;

import com.ivomtdias.springshopapi.model.Stock;
import com.ivomtdias.springshopapi.model.StockStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
    Optional<Stock> findStockById(UUID productId);

    @Modifying
    @Transactional
    @Query("update Stock s set s.quantity = :quantity, s.stockStatus = :stockStatus where s.id = :id")
    void updateStockById(
            @Param(value = "quantity") int quantity,
            @Param(value = "stockStatus") StockStatus stockStatus,
            @Param(value = "id") UUID productId
    );
}
