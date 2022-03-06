package com.lukodev.evorapaint.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @Size(min = 10, message = "IBAN en az 15 karakterden oluşmalıdır.")
    @Size(max = 40, message = "IBAN en fazla 40 karakterden oluşmalıdır.")
    private String iban;

    @Column(name = "account_number")
    @NotNull(message = "Hesap numarası boş bırakılamaz.")
    @NotBlank(message = "Hesap numarası sadece boşluktan oluşamaz.")
    @Size(min = 2, message = "Hesap numarası en az 2 karakterden oluşmalıdır.")
    @Size(max = 30, message = "Hesap numarası en fazla 30 karakterden oluşmalıdır.")
    private String accountNumber;

    @Column(name = "account_type")
    @NotNull(message = "Hesap türü boş bırakılamaz.")
    @NotBlank(message = "Hesap türü sadece boşluktan oluşamaz.")
    @Size(max = 20, message = "Hesap türü en fazla 20 karakterden oluşmalıdır.")
    private String accountType;

    @ManyToOne()
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;
}
