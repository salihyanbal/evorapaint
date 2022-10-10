package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.ShoppingCartItem;
import com.lukodev.evorapaint.entities.dtos.ShoppingCartItemWithImageDto;

import java.util.List;

public interface ShoppingCartItemService {

    Result add(ShoppingCartItem shoppingCartItem);
    Result update(ShoppingCartItem shoppingCartItem);
    Result delete(ShoppingCartItem shoppingCartItem);
    Result deleteAll(List<ShoppingCartItem> shoppingCartItems);
    DataResult<List<ShoppingCartItem>> getAll();
    DataResult<List<ShoppingCartItem>> getAllByShoppingCartId(int shoppingCartId);
    DataResult<List<ShoppingCartItemWithImageDto>> getAllWithImageByShoppingCartId(int shoppingCartId);

    DataResult<ShoppingCartItem> getById(int id);

}
