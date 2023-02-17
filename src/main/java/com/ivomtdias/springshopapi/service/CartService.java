package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.dto.CartProductDTO;

import java.util.UUID;

public interface CartService {
    CartProductDTO addProduct(UUID productId);
    void removeProduct(UUID productId);
}
