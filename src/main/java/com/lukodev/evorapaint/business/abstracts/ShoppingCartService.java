package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.ShoppingCart;
import com.lukodev.evorapaint.entities.concretes.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartService {

    Result add(ShoppingCart shoppingCart);
    Result update(ShoppingCart shoppingCart);
    Result delete(ShoppingCart shoppingCart);
    Result deleteByCustomerId(int customerId);

    DataResult<List<ShoppingCart>> getAll();

    DataResult<ShoppingCart> getById(int id);
    DataResult<ShoppingCart> getByCustomerId(int customerId);
    DataResult<Boolean> existByCustomerId(int customerId);

}
