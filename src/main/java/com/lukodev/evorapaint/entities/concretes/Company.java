package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "company_name")
    @NotNull(message = "Şirket adı boş bırakılamaz.")
    @NotBlank(message = "Şirket adı sadece boşluktan oluşamaz.")
    private String companyName;

    @OneToOne()
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;

}
