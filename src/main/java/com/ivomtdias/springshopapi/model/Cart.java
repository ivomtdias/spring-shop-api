package com.ivomtdias.springshopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // COMMENTED TO MAKE AN UNIDIRECTIONAL RELATIONSHIP
   // @OneToOne(mappedBy = "cart")
    // private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    private List<CartProduct> productList;
}
