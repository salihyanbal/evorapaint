package com.lukodev.evorapaint.entities.dtos;

import com.lukodev.evorapaint.entities.concretes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemWithImageDto {
    private int id;
    private ShoppingCart shoppingCart;
    private Product product;
    private PackageType packageType;
    private int packageTypeCount;
    private int quantity;
    private String imageName;
}
