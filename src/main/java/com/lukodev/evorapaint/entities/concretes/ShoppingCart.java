package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "shipment_type_id")
    private ShipmentType shipmentType;

    @OneToMany(mappedBy = "shoppingCart")
    @JsonIgnore
    private List<ShoppingCartItem> shoppingCartItems;
}
