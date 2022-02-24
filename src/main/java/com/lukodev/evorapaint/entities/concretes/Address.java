package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotNull(message = "Adres başlığı boş bırakılamaz.")
    @NotBlank(message = "Adres başlığı sadece boşluktan oluşamaz.")
    private String title;

    @Column(name = "address_information")
    @NotNull(message = "Adres bilgisi boş bırakılamaz.")
    @NotBlank(message = "Adres bilgisi sadece boşluktan oluşamaz.")
    private String addressInformation;

    @Column(name = "country")
    @NotNull(message = "Ülke boş bırakılamaz.")
    @NotBlank(message = "Ülke sadece boşluktan oluşamaz.")
    private String country;

    @Column(name = "post_code")
    @NotNull(message = "Posta kodu boş bırakılamaz.")
    @NotBlank(message = "Posta kodu boşluktan oluşamaz.")
    private String postCode;

    @Column(name = "contact_number")
    @NotNull(message = "İletişim numarası boş bırakılamaz.")
    @NotBlank(message = "İletişim numarası boşluktan oluşamaz.")
    private String contactNumber;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Order> order;
}
