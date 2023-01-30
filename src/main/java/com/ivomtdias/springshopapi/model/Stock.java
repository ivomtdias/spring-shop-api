package com.ivomtdias.springshopapi.model;

import com.ivomtdias.springshopapi.exception.IllegalStockException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "stock")
public class Stock extends BaseEntity {
    @Id
    @Column(name = "product_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status", nullable = false)
    private StockStatus stockStatus;

    public Stock(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        stockStatus = generateStockStatus(quantity);
    }

    public StockStatus generateStockStatus(int quantity) {
        if(quantity < 0)
            throw new IllegalStockException();
        if(quantity == 0)
            return StockStatus.OUT_OF_STOCK;
        if(quantity <= 10)
            return StockStatus.LOW_STOCK;
        return StockStatus.IN_STOCK;
    }
}
