package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ShoppingCartService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.ShoppingCartDao;
import com.lukodev.evorapaint.entities.concretes.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartManager implements ShoppingCartService {

    private ShoppingCartDao shoppingCartDao;

    @Autowired
    public ShoppingCartManager(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "shoppingCart.getAll",allEntries = true),
            @CacheEvict(value = "shoppingCart.getByCustomerId", key = "#shoppingCart.customer.id", allEntries = true)
    })
    public Result add(ShoppingCart shoppingCart) {
        this.shoppingCartDao.save(shoppingCart);
        return new SuccessResult(Messages.SHOPPING_CART_ADDED);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "shoppingCart.getAll",allEntries = true),
            @CacheEvict(value = "shoppingCart.getByCustomerId", key = "#shoppingCart.customer.id", allEntries = true)
    })
    public Result update(ShoppingCart shoppingCart) {
        this.shoppingCartDao.save(shoppingCart);
        return new SuccessResult(Messages.SHOPPING_CART_UPDATED);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "shoppingCart.getAll",allEntries = true),
            @CacheEvict(value = "shoppingCart.getByCustomerId", key = "#shoppingCart.customer.id", allEntries = true)
    })
    public Result delete(ShoppingCart shoppingCart) {
        this.shoppingCartDao.delete(shoppingCart);
        return new SuccessResult(Messages.SHOPPING_CART_DELETED);
    }

    @Override
    public Result deleteByCustomerId(int customerId) {
        this.shoppingCartDao.deleteByCustomerId(customerId);
        return new SuccessResult(Messages.SHOPPING_CART_DELETED);
    }

    @Override
    @Cacheable(value = "shoppingCart.getAll")
    public DataResult<List<ShoppingCart>> getAll() {
        return new SuccessDataResult<>(this.shoppingCartDao.findAll());
    }

    @Override
    public DataResult<ShoppingCart> getById(int id) {
        return new SuccessDataResult<>(this.shoppingCartDao.findById(id).get());
    }

    @Override
    @Cacheable(value = "shoppingCart.getByCustomerId", key = "#customerId")
    public DataResult<ShoppingCart> getByCustomerId(int customerId) {
        return new SuccessDataResult<>(this.shoppingCartDao.getByCustomerId(customerId));
    }

    @Override
    public DataResult<Boolean> existByCustomerId(int customerId) {
        return new SuccessDataResult<>(this.shoppingCartDao.existsShoppingCartByCustomerId(customerId));
    }
}
