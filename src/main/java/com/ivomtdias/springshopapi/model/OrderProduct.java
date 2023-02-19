package com.ivomtdias.springshopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_product")
public class OrderProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

}
