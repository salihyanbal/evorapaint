package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.ShoppingCartItem;
import com.lukodev.evorapaint.entities.dtos.ShoppingCartItemWithImageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartItemDao extends JpaRepository<ShoppingCartItem, Integer> {

    List<ShoppingCartItem> getAllByShoppingCartId(int shoppingCartId);

   @Query("Select new com.lukodev.evorapaint.entities.dtos.ShoppingCartItemWithImageDto(sci.id, sci.shoppingCart, sci.product, sci.packageType, sci.packageTypeCount, sci.quantity, pi.imageName) " +
            "From ShoppingCartItem sci left join sci.product p left join p.productImage pi where sci.shoppingCart.id = :shoppingCartId")
    List<ShoppingCartItemWithImageDto> getAllWithImageByShoppingCartId(int shoppingCartId);
}
