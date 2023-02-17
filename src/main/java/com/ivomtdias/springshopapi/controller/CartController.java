package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.model.dto.CartProductDTO;
import com.ivomtdias.springshopapi.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @PostMapping("{productId}")
    public ResponseEntity<CartProductDTO> addProductToCart(@PathVariable UUID productId){
        return ResponseEntity.ok(cartService.addProduct(productId));
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @DeleteMapping("{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable UUID productId){
        cartService.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
