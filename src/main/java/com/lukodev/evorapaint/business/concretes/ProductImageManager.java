package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.ProductImageService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.core.utilities.upload.imageUpload.ImageUploadService;
import com.lukodev.evorapaint.dataAccess.abstracts.ProductImageDao;
import com.lukodev.evorapaint.entities.concretes.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageManager implements ProductImageService {

    private ProductImageDao productImageDao;
    private ImageUploadService imageUploadService;

    @Autowired
    public ProductImageManager(ProductImageDao productImageDao, ImageUploadService imageUploadService) {
        this.productImageDao = productImageDao;
        this.imageUploadService = imageUploadService;
    }

    @Override
    public Result add(ProductImage productImage, MultipartFile file) {
        DataResult<?> saveResult = this.imageUploadService.save(file);
        if(!saveResult.isSuccess()){
            return saveResult;
        }
        productImage.setImageName((String) saveResult.getData());
        productImage.setDate(LocalDateTime.now());
        productImageDao.save(productImage);
        return new SuccessResult(Messages.PRODUCT_IMAGE_ADDED);
    }

    @Override
    public Result update(ProductImage productImage, MultipartFile file) {
        productImage = getById(productImage.getId()).getData();
        DataResult<?> deleteResult = this.imageUploadService.delete(productImage.getImageName());
        DataResult<?> saveResult = this.imageUploadService.save(file);
        if(!saveResult.isSuccess()){
            return saveResult;
        }
        productImage.setImageName((String) saveResult.getData());
        productImage.setDate(LocalDateTime.now());
        productImageDao.save(productImage);
        return new SuccessResult(Messages.PRODUCT_IMAGE_UPDATED);
    }

    @Override
    public Result delete(ProductImage productImage) {
        DataResult<?> deleteResult = this.imageUploadService.delete(productImage.getImageName());
        this.productImageDao.delete(productImage);
        if(!deleteResult.isSuccess()){
            return deleteResult;
        }
        return new SuccessResult(Messages.PRODUCT_IMAGE_DELETED);
    }

    @Override
    public DataResult<List<ProductImage>> getAll() {
        return new SuccessDataResult<>(this.productImageDao.findAll());
    }

    @Override
    public DataResult<List<ProductImage>> getAllByProductIds(List<Integer> productIds) {
        return new SuccessDataResult<>(this.productImageDao.getAllByProductIdIn(productIds));
    }

    @Override
    public DataResult<ProductImage> getById(int id) {
        return new SuccessDataResult<>(this.productImageDao.findById(id).get());
    }

    @Override
    public DataResult<ProductImage> getByProductId(int productId) {
        return new SuccessDataResult<>(this.productImageDao.getByProductId(productId));
    }
}
