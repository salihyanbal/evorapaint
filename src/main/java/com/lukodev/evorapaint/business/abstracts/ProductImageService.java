package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    Result add(ProductImage productImage, MultipartFile file);
    Result update(ProductImage productImage, MultipartFile file);
    Result delete(ProductImage productImage);

    DataResult<List<ProductImage>> getAll();
    DataResult<List<ProductImage>> getAllByProductIds(List<Integer> productIds);
    DataResult<ProductImage> getById(int id);
    DataResult<ProductImage> getByProductId(int productId);

}
