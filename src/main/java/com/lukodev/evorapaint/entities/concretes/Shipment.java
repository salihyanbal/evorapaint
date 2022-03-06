package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

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
    @Size(max = 120, message = "Nakliyat takip numarası en fazla 120 karakterden oluşabilir.")
    private String trackingCode;

    @Column(name = "tracking_link")
    @Size(max = 500, message = "Nakliyat takip linki en fazla 500 karakterden oluşabilir.")
    private String trackingLink;

    @OneToOne()
    @JoinColumn(name = "order_id", unique = true)
    private Order order;

    @OneToOne()
    @JoinColumn(name = "shipment_method_id", unique = true)
    private ShipmentMethod shipmentMethod;
}
