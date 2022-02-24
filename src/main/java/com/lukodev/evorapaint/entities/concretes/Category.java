package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Kategori adı boş bırakılamaz.")
    @Min(value = 3, message = "Kategori adı en az 3 harften oluşmalıdır.")
    @Max(value = 30, message = "Kategori adı en fazla 30 harften oluşmalıdır")
    private String name;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "category")
    @JsonIgnore()
    private List<Product> products;
}
