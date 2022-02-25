package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipment_types")
public class ShipmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Nakliyat tipi adı boş bırakılamaz.")
    @NotBlank(message = "Nakliyat tipi adı sadece boşluktan oluşamaz.")
    @Max(value = 50, message = "Nakliyat tipi adı en fazla 50 harften oluşabilir.")
    private String name;

    @OneToMany(mappedBy = "shipmentType")
    @JsonIgnore
    private List<ShipmentMethod> shipmentMethods;

    @OneToMany(mappedBy = "shipmentType")
    @JsonIgnore
    private List<PackageType> packageTypes;
}
