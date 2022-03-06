package com.lukodev.evorapaint.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Size(min = 2, message = "Ürün adı en az 2 harften oluşmalıdır.")
    @Size(max = 50, message = "Ürün adı en fazla 50 harften oluşmalıdır.")
    private String name;

    @Column(name = "weight")
    @Min(value = 0, message = "Ürün ağırlığı 0'dan az olamaz.")
    @Max(value = 100000, message = "Ürün ağırlığı 100.000'dan fazla olamaz.")
    private int weight;

    @Column(name = "volume")
    @NotNull(message = "Hacim değeri boş bırakılamaz.")
    @Min(value = 0, message = "Ürün hacmi 0'dan küçük olamaz.")
    @Max(value = 10000, message = "Ürün hacmi 10.000'den büyük olamaz.")
    private double volume;

    @Column(name = "unit_price")
    @NotNull(message = "Ürün birim fiyatı boş bırakılamaz.")
    @Min(value = 0, message = "Ürün birim fiyatı 0'dan küçük olamaz.")
    @Max(value = 10000000, message = "Ürün birim fiyatı 10.000.000'dan büyük olamaz.")
    private double unitPrice;

    @Column(name = "units_in_stock")
    @NotNull(message = "Stoktaki ürün miktarı boş bırakılamaz.")
    @Min(value = 0, message = "Stoktaki ürün miktarı 0'dan küçük olamaz.")
    @Max(value = 10000000, message = "Stoktaki ürün miktarı 10.000.000'dan büyük olamaz.")
    private int unitsInStock;

    @Column(name = "description")
    @NotNull(message = "Ürün açıklaması boş bırakılamaz.")
    @Size(max = 400, message = "Ürün açıklaması en fazla 400 harften oluşmalıdır.")
    private String description;

    @Column(name = "active")
    private boolean active;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderProduct> orderProducts;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ShoppingCartItem> shoppingCarts;

    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private ProductImage productImage;
}
