package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> getAllByCategoryId(int categoryId);

    @Query(nativeQuery=true, value="SELECT *  FROM products ORDER BY random() limit :amount")
    List<Product> getRandomProducts(int amount);

}
