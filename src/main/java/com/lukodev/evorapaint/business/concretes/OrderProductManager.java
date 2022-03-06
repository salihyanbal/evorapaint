package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.OrderProductService;
import com.lukodev.evorapaint.business.abstracts.ProductService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.*;
import com.lukodev.evorapaint.dataAccess.abstracts.OrderProductDao;
import com.lukodev.evorapaint.entities.concretes.OrderProduct;
import com.lukodev.evorapaint.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProductManager implements OrderProductService {

    private OrderProductDao orderProductDao;
    private ProductService productService;

    @Autowired
    public OrderProductManager(OrderProductDao orderProductDao, ProductService productService) {
        this.orderProductDao = orderProductDao;
        this.productService = productService;
    }

    @Override
    @CacheEvict(value = "orderProduct.getAll", allEntries = true)
    public Result add(OrderProduct orderProduct) {
        DataResult<Boolean> stockResult = this.productService.isStockAvailable(orderProduct.getProduct(), orderProduct.getQuantity());
        if(!stockResult.getData()){
            return new ErrorResult(Messages.PRODUCT_HAS_NO_STOCK + " " + orderProduct.getProduct().getName());
        }
        this.orderProductDao.save(orderProduct);
        return new SuccessResult(Messages.ORDER_PRODUCT_ADDED);
    }

    @Override
    @CacheEvict(value = "orderProduct.getAll", allEntries = true)
    @Transactional
    public Result addAll(List<OrderProduct> orderProducts) {
        List<Product> productsToUpdate = new ArrayList<>();
        for (OrderProduct orderProduct: orderProducts){
            DataResult<Boolean> stockResult = this.productService.isStockAvailable(orderProduct.getProduct(), orderProduct.getQuantity());
            if(!stockResult.getData()){
                return new ErrorResult(Messages.PRODUCT_HAS_NO_STOCK + " " + orderProduct.getProduct().getName());
            }
            Product productToUpdate = orderProduct.getProduct();
            productToUpdate.setUnitsInStock(productToUpdate.getUnitsInStock() - orderProduct.getQuantity());;
            productsToUpdate.add(productToUpdate);
        }
        this.productService.updateAll(productsToUpdate);
        this.orderProductDao.saveAll(orderProducts);
        return new SuccessResult(Messages.ORDER_PRODUCTS_ADDED);
    }

    @Override
    @CacheEvict(value = "orderProduct.getAll", allEntries = true)
    public Result update(OrderProduct orderProduct) {
        this.orderProductDao.save(orderProduct);
        return new SuccessResult(Messages.ORDER_PRODUCT_UPDATED);
    }

    @Override
    @CacheEvict(value = "orderProduct.getAll", allEntries = true)
    public Result delete(OrderProduct orderProduct) {
        this.orderProductDao.delete(orderProduct);
        return new SuccessResult(Messages.ORDER_PRODUCT_DELETED);
    }

    @Override
    @Cacheable(value = "orderProduct.getAll")
    public DataResult<List<OrderProduct>> getAll() {
        return new SuccessDataResult<>(this.orderProductDao.findAll());
    }

    @Override
    public DataResult<List<OrderProduct>> getAllByOrderId(int orderId) {
        return new SuccessDataResult<>(this.orderProductDao.getAllByOrderId(orderId));
    }

    @Override
    public DataResult<OrderProduct> getById(int id) {
        return new SuccessDataResult<>(this.orderProductDao.findById(id).get());
    }
}
