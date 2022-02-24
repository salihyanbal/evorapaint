package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Product;

import java.util.List;

public interface ProductService {

    Result add(Product product);
    Result update(Product product);
    Result delete(Product product);

    DataResult<List<Product>> getAll();
    DataResult<List<Product>> getAllByCategoryId(int categoryId);
    DataResult<List<Product>> getRandom(int amount);

    DataResult<Product> getById(int id);

}
