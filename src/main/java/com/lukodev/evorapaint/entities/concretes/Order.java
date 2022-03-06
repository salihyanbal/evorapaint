package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Sipariş tarihi boş bırakılamaz.")
    private LocalDateTime date;

    @Column(name = "estimated_delivery_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate estimatedDeliveryDate;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @ManyToOne()
    @JoinColumn(name = "shipment_method_id")
    private ShipmentMethod shipmentMethod;

    @ManyToOne()
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(mappedBy = "order")
    @JsonIgnore
    private Shipment shipment;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderProduct> orderProducts;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<Payment> payments;
}
