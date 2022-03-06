package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "package_types")
public class PackageType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    @NotNull(message = "Kapasite hacim değeri boş bırakılamaz.")
    @Min(value = 0, message = "Kapasite hacim 0'dan küçük olamaz.")
    @Max(value = 100000000, message = "Kapasite hacim 100.000.000'den büyük olamaz.")
    private double capacity;

    @ManyToOne()
    @JoinColumn(name = "used_shipment_type_id")
    private ShipmentType shipmentType;

    @OneToMany()
    @JsonIgnore
    private List<OrderProduct> orderProducts;

    @OneToMany()
    @JsonIgnore
    private List<ShoppingCartItem> shoppingCartItems;
}
