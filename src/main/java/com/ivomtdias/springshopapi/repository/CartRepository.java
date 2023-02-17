package com.ivomtdias.springshopapi.repository;

import com.ivomtdias.springshopapi.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    //@Deprecated
    //@Query("select c from Cart c where c.user.id = :userId")
    //Optional<Cart> findCartByUserId(@Param("userId") UUID userId);
}
