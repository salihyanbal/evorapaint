package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "remittance_informations")
public class RemittanceInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "branch_name")
    @NotNull(message = "Şube adı boş bırakılamaz.")
    @NotBlank(message = "Şube adı sadece boşluktan oluşamaz.")
    private String branchName;

    @Column(name = "branch_code")
    @NotNull(message = "Şube kodu boş bırakılamaz.")
    @NotBlank(message = "Şube kodu sadece boşluktan oluşamaz.")
    private String branchCode;

    @Column(name = "iban")
    @NotNull(message = "IBAN boş bırakılamaz.")
    @NotBlank(message = "IBAN sadece sadece boşluktan oluşamaz.")
    @Min(value = 15, message = "IBAN en az 15 karakterden oluşmalıdır.")
    @Max(value = 40, message = "IBAN en fazla 40 karakterden oluşmalıdır.")
    private String iban;

    @Column(name = "account_number")
    @NotNull(message = "Hesap numarası boş bırakılamaz.")
    @NotBlank(message = "Hesap numarası sadece boşluktan oluşamaz.")
    @Min(value = 2, message = "Hesap numarası en az 2 karakterden oluşmalıdır.")
    @Max(value = 30, message = "Hesap numarası en fazla 30 karakterden oluşmalıdır.")
    private String accountNumber;

    @Column(name = "account_type")
    @NotNull(message = "Hesap türü boş bırakılamaz.")
    @NotBlank(message = "Hesap türü sadece boşluktan oluşamaz.")
    @Max(value = 20, message = "Hesap türü en fazla 20 karakterden oluşmalıdır.")
    private String accountType;

    @ManyToOne()
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;
}
