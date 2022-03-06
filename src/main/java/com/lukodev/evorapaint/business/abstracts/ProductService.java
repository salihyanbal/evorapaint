package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Product;
import com.lukodev.evorapaint.entities.dtos.ProductWithImageDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Result add(Product product);
    Result update(Product product);
    Result updateAll(List<Product> products);
    Result delete(Product product);

    DataResult<List<Product>> getAll();
    DataResult<List<Product>> getAllActive();
    DataResult<List<Product>> getAllByCategoryId(int categoryId);
    DataResult<List<Product>> getAllByCategoryIdAndActive(int categoryId);
    DataResult<List<Product>> getRandom(int amount);
    DataResult<List<Product>> getRandomActive(int amount);
    DataResult<List<ProductWithImageDto>> getAllActiveWithImage();
    DataResult<List<ProductWithImageDto>> getAllByCategoryIdAndActiveWithImage(int categoryId);
    DataResult<List<ProductWithImageDto>> getAllProductsWithImageByOrderId(int orderId);

    DataResult<Product> getById(int id);
    DataResult<ProductWithImageDto> getByIdWithImage(int id);

    DataResult<Boolean> isStockAvailable(Product product, int quantity);
    DataResult<Boolean> isStockAvailable(Map<Product, Integer> productsWithQuantities);

}
