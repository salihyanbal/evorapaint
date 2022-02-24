package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.OrderProductService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.OrderProductDao;
import com.lukodev.evorapaint.entities.concretes.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductManager implements OrderProductService {

    private OrderProductDao orderProductDao;

    @Autowired
    public OrderProductManager(OrderProductDao orderProductDao) {
        this.orderProductDao = orderProductDao;
    }

    @Override
    @CacheEvict(value = "orderProduct.getAll", allEntries = true)
    public Result add(OrderProduct orderProduct) {
        this.orderProductDao.save(orderProduct);
        return new SuccessResult(Messages.ORDER_PRODUCT_ADDED);
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
