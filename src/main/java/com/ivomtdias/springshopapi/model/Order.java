package com.ivomtdias.springshopapi.model;

import com.ivomtdias.springshopapi.statemachine.order.OrderState;
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
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderProduct> productList;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state", nullable = false)
    OrderState orderState;
}
