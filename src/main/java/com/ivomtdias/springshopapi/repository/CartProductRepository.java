package com.ivomtdias.springshopapi.repository;

import com.ivomtdias.springshopapi.model.CartProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartProductRepository extends JpaRepository<CartProduct, UUID> {
    @Query(value = "select cp from CartProduct cp " +
            "where cp.cart.id = :cartId and cp.product.id = :productId " +
            "order by cp.createdAt desc"
    )
    List<Optional<CartProduct>> findByCartIdAndProductId(@Param("cartId") UUID cartId,
                                                         @Param("productId") UUID productId);

    @Query(value = "select cp from CartProduct cp where cp.cart.id = :cartId")
    List<CartProduct> findByCartId(@Param("cartId") UUID cartId);

    @Modifying
    @Transactional
    @Query(value = "delete from CartProduct where cart.id = :cartId")
    void deleteCartProductsByCartId(@Param("cartId") UUID cartId);
}
