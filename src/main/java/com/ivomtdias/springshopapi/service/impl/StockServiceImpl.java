package com.ivomtdias.springshopapi.service.impl;

import com.ivomtdias.springshopapi.exception.StockNotFoundException;
import com.ivomtdias.springshopapi.model.Stock;
import com.ivomtdias.springshopapi.model.StockStatus;
import com.ivomtdias.springshopapi.repository.StockRepository;
import com.ivomtdias.springshopapi.service.StockService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void addStock(UUID productId, int quantityToAdd) {
        Optional<Stock> stock = stockRepository.findStockById(productId);
        if(stock.isEmpty())
            throw new StockNotFoundException(productId);

        stockRepository.updateStockById(
                stock.get().getQuantity() + quantityToAdd,
                calculateStockStatus(stock.get(), quantityToAdd),
                productId
        );
    }

    @Override
    public void removeStock(UUID productId, int quantityToRemove) {
        Optional<Stock> stock = stockRepository.findStockById(productId);
        if(stock.isEmpty())
            throw new StockNotFoundException(productId);

        stockRepository.updateStockById(
                stock.get().getQuantity() - quantityToRemove,
                calculateStockStatus(stock.get(), quantityToRemove * -1),
                productId
        );
    }

    @Override
    public Set<Stock> findAll() {
        return new HashSet<>(stockRepository.findAll());
    }

    @Override
    public Boolean checkIfItIsInStock(UUID productId, int quantity) {
        Optional<Stock> stock = stockRepository.findStockById(productId);
        if(stock.isEmpty())
            throw new StockNotFoundException(productId);

        return quantity <= stock.get().getQuantity();
    }

    @Override
    public Stock getStockStatus(UUID productId) {
        Optional<Stock> stock = stockRepository.findStockById(productId);
        if(stock.isEmpty())
            throw new StockNotFoundException(productId);
        return stock.get();
    }

    private StockStatus calculateStockStatus(Stock stock, int quantity) {
        return stock.generateStockStatus(stock.getQuantity() + quantity);
    }
}
