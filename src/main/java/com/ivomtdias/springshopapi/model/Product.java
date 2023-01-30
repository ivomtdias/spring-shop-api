package com.ivomtdias.springshopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "product_name_unique",
                        columnNames = "name"
                )
        })
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private double price;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Stock stock;
}
