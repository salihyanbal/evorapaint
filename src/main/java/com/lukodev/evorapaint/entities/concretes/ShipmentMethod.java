package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipment_methods")
public class ShipmentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Sevkiyat yöntemi adı boş bırakılamaz.")
    @NotBlank(message = "Sevkiyat yöntemi adı sadece boşluktan oluşamaz.")
    private String name;

    @Column(name = "company")
    @NotNull(message = "Sevkiyat şirketi boş bırakılamaz.")
    @NotBlank(message = "Sevkiyat şirketi sadece boşluktan oluşamaz.")
    private String company;

    @Column(name = "shipping_cost")
    @Min(value = 0, message = "Sevkiyat maliyeti(ücreti) 0'dan küçük olamaz.")
    @Max(value = 1000000, message = "Sevkiyat maliyeti(ücreti) 1.000.000'dan büyük olamaz.")
    private double shippingCost;

    @Column(name = "company_web_address")
    private String companyWebAddress;

    @Column(name = "company_contact_number")
    private String companyContactNumber;

    @ManyToOne()
    @JoinColumn(name = "shipment_type_id")
    private ShipmentType shipmentType;

    @OneToMany(mappedBy = "shipmentMethod")
    @JsonIgnore
    private List<Order> orders;

    @OneToOne(mappedBy = "shipmentMethod")
    @JsonIgnore
    private Shipment shipment;

}
