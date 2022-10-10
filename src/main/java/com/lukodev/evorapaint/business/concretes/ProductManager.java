package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ProductService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.ProductDao;
import com.lukodev.evorapaint.entities.concretes.Product;
import com.lukodev.evorapaint.entities.dtos.ProductWithImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Async
    @CacheEvict(value = "product.getall", allEntries = true)
    public Result updateAll(List<Product> products) {
        this.productDao.saveAll(products);
        return new SuccessResult(Messages.PRODUCTS_UPDATED);
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
    public DataResult<List<Product>> getAllActive() {
        return new SuccessDataResult<>(this.productDao.getAllByActiveTrue());
    }

    @Override
    public DataResult<List<Product>> getAllByCategoryId(int categoryId) {
        return new SuccessDataResult<>(this.productDao.getAllByCategoryId(categoryId));
    }

    @Override
    public DataResult<List<Product>> getAllByCategoryIdAndActive(int categoryId) {
        return new SuccessDataResult<>(this.productDao.getAllByCategoryIdAndActiveTrue(categoryId));
    }

    @Override
    public DataResult<List<Product>> getRandom(int amount) {
        return new SuccessDataResult<>(this.productDao.getRandomProducts(amount));

    }

    @Override
    public DataResult<List<Product>> getRandomActive(int amount) {
        return new SuccessDataResult<>(this.productDao.getRandomActiveProducts(amount));
    }

    @Override
    public DataResult<List<ProductWithImageDto>> getAllActiveWithImage() {
        return new SuccessDataResult<>(this.productDao.getAllActiveWithImage());
    }

    @Override
    public DataResult<List<ProductWithImageDto>> getAllByCategoryIdAndActiveWithImage(int categoryId) {
        return new SuccessDataResult<>(this.productDao.getAllByCategoryIdAndActiveWithImage(categoryId));
    }

    @Override
    public DataResult<List<ProductWithImageDto>> getAllProductsWithImageByOrderId(int orderId) {
        return new SuccessDataResult<>(this.productDao.getAllProductsWithImageByOrderId(orderId));
    }

    @Override
    public DataResult<Product> getById(int id) {
        return new SuccessDataResult<>(this.productDao.findById(id).get());
    }

    @Override
    public DataResult<ProductWithImageDto> getByIdWithImage(int id) {
        return new SuccessDataResult<>(this.productDao.getByIdWithImage(id));
    }

    @Override
    public DataResult<Boolean> isStockAvailable(Product product, int quantity) {
        Product productToCheck = this.getById(product.getId()).getData();
        if(productToCheck.getUnitsInStock() - quantity <0){
            return new SuccessDataResult<>(false);
        }
        return new SuccessDataResult<>(true);
    }

    @Override
    public DataResult<Boolean> isStockAvailable(Map<Product, Integer> productWithQuantities) {
        for(Map.Entry<Product, Integer> productWithQuantity: productWithQuantities.entrySet()){
            if(!this.isStockAvailable(productWithQuantity.getKey(), productWithQuantity.getValue()).getData()){
                return new SuccessDataResult<>(false);
            }
        }
        return new SuccessDataResult<>(true);
    }
}
