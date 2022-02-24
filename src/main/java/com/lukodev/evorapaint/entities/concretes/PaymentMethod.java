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
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Ödeme yöntemi ismi boş bırakılamaz.")
    @NotBlank(message = "Ödeme yöntemi ismi sadece boşluktan oluşamaz.")
    private String name;

    @Column(name = "commission")
    @Min(value = 0, message = "Komisyon değeri 0'dan küçük olamaz.")
    @Max(value = 100000, message = "Komisyon değeri 100.000'den büyük olamaz.")
    private double commission;

    @OneToMany(mappedBy = "paymentMethod")
    @JsonIgnore()
    private List<RemittanceInformation> remittanceInformations;

    @OneToMany(mappedBy = "paymentMethod")
    @JsonIgnore
    private List<Payment> payments;
}
