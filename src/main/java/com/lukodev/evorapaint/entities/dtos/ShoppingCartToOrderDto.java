package com.lukodev.evorapaint.entities.dtos;

import com.lukodev.evorapaint.entities.concretes.Address;
import com.lukodev.evorapaint.entities.concretes.PackageType;
import com.lukodev.evorapaint.entities.concretes.ShipmentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartToOrderDto {
    private int shoppingCartId;
    private ShipmentMethod shipmentMethod;
    private Address address;
    private boolean belongingToCompany;
}
