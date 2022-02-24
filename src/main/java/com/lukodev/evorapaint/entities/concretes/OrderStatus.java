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
@Table(name = "order_statuses")
public class OrderStatus {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Sipariş statüsü ismi boş bırakılamaz.")
    @NotBlank(message = "Sipariş statüsü ismi sadece boşluktan oluşamaz.")
    private String name;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "orderStatus")
    @JsonIgnore
    private List<Order> orders;
}
