package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tracking_code")
    @Max(value = 120, message = "Kargo takip numarası en fazla 120 karakterden oluşabilir.")
    private String trackingCode;

    @Column(name = "tracking_link")
    @Max(value = 500, message = "Kargo takip linki en fazla 500 karakterden oluşabilir.")
    private String trackingLink;

    @OneToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne()
    @JoinColumn(name = "shipment_method_id")
    private ShipmentMethod shipmentMethod;
}
