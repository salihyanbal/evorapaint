package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount")
    @NotNull(message = "Ödeme miktarı boş bırakılamaz.")
    @Min(value = 0, message = "Ödeme miktarı 0'dan küçük olamaz.")
    @Max(value = 100000000, message = "Ödeme miktarı 100.000.000'dan büyük olamaz.")
    private double amount;

    @Column(name = "currency")
    @NotNull(message = "Para birimi boş bırakılamaz.")
    @NotBlank(message = "Para birimi sadece boşluktan oluşamaz.")
    private String currency;

    @Column(name = "paid_by")
    @NotNull(message = "Ödeyen kişi adı ve soyadı boş bırakılamaz.")
    @NotBlank(message = "Ödeyen kişi adı ve soyadı sadece boşluktan oluşamaz.")
    private String paidBy;

    @Column(name = "date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Ödeme tarihi ve saati boş bırakılamaz.")
    private Date date;

    @Column(name = "verified")
    private boolean verified;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

}
