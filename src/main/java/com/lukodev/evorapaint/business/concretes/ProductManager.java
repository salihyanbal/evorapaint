package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ProductService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.ProductDao;
import com.lukodev.evorapaint.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @CacheEvict(value = "product.getall", allEntries = true)
    public Result add(Product product) {
        this.productDao.save(product);
        return new SuccessResult(Messages.PRODUCT_ADDED);
    }

    @Override
    @CacheEvict(value = "product.getall", allEntries = true)
    public Result update(Product product) {
        this.productDao.save(product);
        return new SuccessResult(Messages.PRODUCT_UPDATED);
    }

    @Override
    @CacheEvict(value = "product.getall", allEntries = true)
    public Result delete(Product product) {
        this.productDao.delete(product);
        return new SuccessResult(Messages.PRODUCT_DELETED);
    }

    @Override
    @Cacheable("product.getall")
    public DataResult<List<Product>> getAll() {
        return new SuccessDataResult<>(this.productDao.findAll());
    }

    @Override
    public DataResult<List<Product>> getAllByCategoryId(int categoryId) {
        return new SuccessDataResult<>(this.productDao.getAllByCategoryId(categoryId));
    }

    @Override
    public DataResult<List<Product>> getRandom(int amount) {
        return new SuccessDataResult<>(this.productDao.getRandomProducts(amount));

    }

    @Override
    public DataResult<Product> getById(int id) {
        return new SuccessDataResult<>(this.productDao.findById(id).get());
    }
}
