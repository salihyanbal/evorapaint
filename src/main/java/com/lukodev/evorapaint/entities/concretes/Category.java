package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(min = 2, message = "Kategori adı en az 2 harften oluşmalıdır.")
    @Size(max = 30, message = "Kategori adı en fazla 30 harften oluşmalıdır")
    private String name;

    @Column(name = "active")
    private boolean active;

    @OneToMany(mappedBy = "category")
    @JsonIgnore()
    private List<Product> products;
}
