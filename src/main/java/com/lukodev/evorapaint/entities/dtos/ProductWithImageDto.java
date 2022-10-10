package com.lukodev.evorapaint.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lukodev.evorapaint.entities.concretes.Category;
import com.lukodev.evorapaint.entities.concretes.OrderProduct;
import com.lukodev.evorapaint.entities.concretes.ProductImage;
import com.lukodev.evorapaint.entities.concretes.ShoppingCartItem;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithImageDto {

    private int id;

    private String name;

    private int grossWeight;

    private int netWeight;

    private double volume;

    private double unitPrice;

    private int unitsInStock;

    private String description;

    private boolean active;

    private Category category;

    private String imageName;
}
