package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.CartProduct;
import com.ivomtdias.springshopapi.model.dto.CartProductDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {
    CartProductDTO addProduct(UUID productId);
    void removeProduct(UUID productId);
    void clearCart();
    List<CartProduct> getProducts();
}
