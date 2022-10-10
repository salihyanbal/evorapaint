package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ProductService;
import com.lukodev.evorapaint.business.abstracts.ShoppingCartItemService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.*;
import com.lukodev.evorapaint.dataAccess.abstracts.ShoppingCartItemDao;
import com.lukodev.evorapaint.entities.concretes.ShoppingCartItem;
import com.lukodev.evorapaint.entities.dtos.ShoppingCartItemWithImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemManager implements ShoppingCartItemService {

    private ShoppingCartItemDao shoppingCartItemDao;
    private ProductService productService;

    @Autowired
    public ShoppingCartItemManager(ShoppingCartItemDao shoppingCartItemDao, ProductService productService) {
        this.shoppingCartItemDao = shoppingCartItemDao;
        this.productService = productService;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "shoppingCartItem.getAll",allEntries = true),
            @CacheEvict(value = "shoppingCartItem.getAllByShoppingCartId", key = "#shoppingCartItem.shoppingCart.id", condition="#shoppingCartItem.shoppingCart!=null"),
            @CacheEvict(value = "shoppingCartItem.getAllWithImageByShoppingCartId", key = "#shoppingCartItem.shoppingCart.id", condition="#shoppingCartItem.shoppingCart!=null")
    })
    public Result add(ShoppingCartItem shoppingCartItem) {
        DataResult<Boolean> stockResult = this.productService.isStockAvailable(shoppingCartItem.getProduct(), shoppingCartItem.getQuantity());
        if(!stockResult.getData()){
            return new ErrorResult(Messages.PRODUCT_HAS_NO_STOCK);
        }
        this.shoppingCartItemDao.save(shoppingCartItem);
        return new SuccessResult(Messages.SHOPPING_CART_ITEM_ADDED);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "shoppingCartItem.getAll",allEntries = true),
            @CacheEvict(value = "shoppingCartItem.getAllByShoppingCartId", key = "#shoppingCartItem.shoppingCart.id", condition="#shoppingCartItem.shoppingCart!=null"),
            @CacheEvict(value = "shoppingCartItem.getAllWithImageByShoppingCartId", key = "#shoppingCartItem.shoppingCart.id", condition="#shoppingCartItem.shoppingCart!=null")
    })
    public Result update(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItemDao.save(shoppingCartItem);
        return new SuccessResult(Messages.SHOPPING_CART_ITEM_UPDATED);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "shoppingCartItem.getAll",allEntries = true),
            @CacheEvict(value = "shoppingCartItem.getAllByShoppingCartId", key = "#shoppingCartItem.shoppingCart.id", condition="#shoppingCartItem.shoppingCart!=null"),
            @CacheEvict(value = "shoppingCartItem.getAllWithImageByShoppingCartId", key = "#shoppingCartItem.shoppingCart.id", condition="#shoppingCartItem.shoppingCart!=null", allEntries = true)
    })
    public Result delete(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItemDao.delete(shoppingCartItem);
        return new SuccessResult(Messages.SHOPPING_CART_ITEM_DELETED);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "shoppingCartItem.getAll",allEntries = true)
    })
    public Result deleteAll(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItemDao.deleteAll(shoppingCartItems);
        return new SuccessResult(Messages.SHOPPING_CART_ITEMS_DELETED);
    }

    @Override
    @Cacheable(value = "shoppingCartItem.getAll")
    public DataResult<List<ShoppingCartItem>> getAll() {
        return new SuccessDataResult<>(this.shoppingCartItemDao.findAll());
    }

    @Cacheable(value = "shoppingCartItem.getAllByShoppingCartId", key = "#shoppingCartId")
    @Override
    public DataResult<List<ShoppingCartItem>> getAllByShoppingCartId(int shoppingCartId) {
        return new SuccessDataResult<>(this.shoppingCartItemDao.getAllByShoppingCartId(shoppingCartId));
    }

    @Cacheable(value = "shoppingCartItem.getAllWithImageByShoppingCartId", key = "#shoppingCartId")
    @Override
    public DataResult<List<ShoppingCartItemWithImageDto>> getAllWithImageByShoppingCartId(int shoppingCartId) {
        return new SuccessDataResult<>(this.shoppingCartItemDao.getAllWithImageByShoppingCartId(shoppingCartId));
    }

    @Override
    public DataResult<ShoppingCartItem> getById(int id) {
        return new SuccessDataResult<>(this.shoppingCartItemDao.findById(id).get());
    }
}
