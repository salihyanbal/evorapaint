package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_products", uniqueConstraints = {@UniqueConstraint(name = "junction_unique",columnNames = { "order_id", "product_id" })})
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quantity")
    @Min(value = 1, message = "Ürün miktarı 1'den küçük olamaz.")
    @Max(value = 100000000, message = "Ürün miktarı 100.000.000'dan büyük olamaz.")
    private int quantity;


    @Column(name = "package_type_count")
    private int packageTypeCount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "package_type_id")
    private PackageType packageType;
}
