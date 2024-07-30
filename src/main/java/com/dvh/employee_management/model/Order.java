package com.dvh.employee_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "order_date",nullable = false)
    private String orderDate;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Employee customer;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
}
