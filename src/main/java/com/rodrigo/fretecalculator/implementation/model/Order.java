package com.rodrigo.fretecalculator.implementation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private UUID id;

    @Column(name = "client_name")
    private String clientName;

    private String destination;

    @Column(name = "total_weight")
    private double totalWeight;

    private double value;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;
}
