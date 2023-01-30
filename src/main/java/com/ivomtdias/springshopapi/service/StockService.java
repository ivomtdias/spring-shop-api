package com.ivomtdias.springshopapi.service;

import com.ivomtdias.springshopapi.model.Stock;

import java.util.Set;
import java.util.UUID;

public interface StockService {
    void addStock(UUID productId, int quantityToAdd);
    void removeStock(UUID productId, int quantityToRemove);
    Set<Stock> findAll();
}
