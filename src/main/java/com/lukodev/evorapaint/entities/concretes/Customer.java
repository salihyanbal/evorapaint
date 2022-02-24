package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "customers")
public class Customer extends User{

    @Column(name = "phone_number")
    @Digits(integer = 20, fraction = 0)
    private String phoneNumber;

    @Column(name = "main_country")
    @NotNull(message = "Yaşanan ülke boş bırakılamaz.")
    @NotBlank(message = "Yaşanan ülke sadece boşluktan oluşamaz.")
    private String mainCountry;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders;
}
