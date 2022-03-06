package com.lukodev.evorapaint.dataAccess.abstracts;

import com.lukodev.evorapaint.entities.concretes.Product;
import com.lukodev.evorapaint.entities.dtos.ProductWithImageDto;
import com.lukodev.evorapaint.entities.dtos.ShoppingCartItemWithImageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> getAllByActiveTrue();
    List<Product> getAllByCategoryId(int categoryId);
    List<Product> getAllByCategoryIdAndActiveTrue(int categoryId);

    @Query("Select new com.lukodev.evorapaint.entities.dtos.ProductWithImageDto(p.id, p.name, p.weight, p.volume, p.unitPrice, p.unitsInStock, p.description, p.active, p.category, pi.imageName) " +
            "From Product p left join p.productImage pi")
    List<ProductWithImageDto> getAllWithImage();

    @Query("Select new com.lukodev.evorapaint.entities.dtos.ProductWithImageDto(p.id, p.name, p.weight, p.volume, p.unitPrice, p.unitsInStock, p.description, p.active, p.category, pi.imageName) " +
            "From Product p left join p.productImage pi where p.active = true")
    List<ProductWithImageDto> getAllActiveWithImage();

    @Query("Select new com.lukodev.evorapaint.entities.dtos.ProductWithImageDto(p.id, p.name, p.weight, p.volume, p.unitPrice, p.unitsInStock, p.description, p.active, p.category, pi.imageName) " +
            "From Product p left join p.productImage pi where p.active = true and p.category.id = :categoryId")
    List<ProductWithImageDto> getAllByCategoryIdAndActiveWithImage(int categoryId);


    @Query("Select new com.lukodev.evorapaint.entities.dtos.ProductWithImageDto(p.id, p.name, p.weight, p.volume, p.unitPrice, p.unitsInStock, p.description, p.active, p.category, pi.imageName) " +
            "From OrderProduct op left join op.product p left join p.productImage pi where op.order.id = :orderId")
    List<ProductWithImageDto> getAllProductsWithImageByOrderId(int orderId);

    @Query(nativeQuery=true, value="SELECT *  FROM products ORDER BY random() limit :amount")
    List<Product> getRandomProducts(int amount);

    @Query(nativeQuery=true, value="SELECT *  FROM products where active=true ORDER BY random() limit :amount")
    List<Product> getRandomActiveProducts(int amount);

    @Query("Select new com.lukodev.evorapaint.entities.dtos.ProductWithImageDto(p.id, p.name, p.weight, p.volume, p.unitPrice, p.unitsInStock, p.description, p.active, p.category, pi.imageName) " +
            "From Product p left join p.productImage pi where p.id = :id")
    ProductWithImageDto getByIdWithImage(int id);

}
