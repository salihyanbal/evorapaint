package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageDao extends JpaRepository<ProductImage, Integer> {

    ProductImage getByProductId(int productId);

    List<ProductImage> getAllByProductIdIn(List<Integer> productIds);
}
