package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "package_type")
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
    @Max(value = 1000000, message = "Kapasite hacim 1.000.000'den büyük olamaz.")
    private double capacity;

    @ManyToOne()
    @JoinColumn(name = "used_shipment_type_id")
    private ShipmentType shipmentType;
}
