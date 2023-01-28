package com.ivomtdias.springshopapi.repository;

import com.ivomtdias.springshopapi.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductByNameIgnoreCase(String productName);
    List<Product> findProductsByNameContainingIgnoreCase(String productName);
    @Transactional
    @Modifying
    @Query("update Product p set p.name = :name, p.price = :price where p.id = :id")
    void updateProductById(
            @Param(value = "name") String productName,
            @Param(value = "price") double price,
            @Param(value = "id") UUID productId
    );
}
