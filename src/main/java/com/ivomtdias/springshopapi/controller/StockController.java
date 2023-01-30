package com.ivomtdias.springshopapi.controller;

import com.ivomtdias.springshopapi.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @PutMapping("add/{productId}/{quantityToAdd}")
    public ResponseEntity<Void> addStock(@PathVariable UUID productId, @PathVariable int quantityToAdd){
        stockService.addStock(productId, quantityToAdd);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ADMIN", "CUSTOMER"})
    @PutMapping("remove/{productId}/{quantityToRemove}")
    public ResponseEntity<Void> removeStock(@PathVariable UUID productId, @PathVariable int quantityToRemove){
        stockService.removeStock(productId, quantityToRemove);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
