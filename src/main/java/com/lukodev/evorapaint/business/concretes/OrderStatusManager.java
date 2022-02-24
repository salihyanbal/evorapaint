package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.OrderStatusService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.OrderStatusDao;
import com.lukodev.evorapaint.entities.concretes.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusManager implements OrderStatusService {

    private OrderStatusDao orderStatusDao;

    @Autowired
    public OrderStatusManager(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    @CacheEvict(value = "orderStatus.getAll", allEntries = true)
    public Result add(OrderStatus orderStatus) {
        this.orderStatusDao.save(orderStatus);
        return new SuccessResult(Messages.ORDER_STATUS_ADDED);
    }

    @Override
    @CacheEvict(value = "orderStatus.getAll", allEntries = true)
    public Result update(OrderStatus orderStatus) {
        this.orderStatusDao.save(orderStatus);
        return new SuccessResult(Messages.ORDER_STATUS_UPDATED);
    }

    @Override
    @CacheEvict(value = "orderStatus.getAll", allEntries = true)
    public Result delete(OrderStatus orderStatus) {
        this.orderStatusDao.delete(orderStatus);
        return new SuccessResult(Messages.ORDER_STATUS_DELETED);
    }

    @Override
    @Cacheable(value = "orderStatus.getAll")
    public DataResult<List<OrderStatus>> getAll() {
        return new SuccessDataResult<>(this.orderStatusDao.findAll());
    }

    @Override
    public DataResult<OrderStatus> getById(int id) {
        return new SuccessDataResult<>(this.orderStatusDao.findById(id).get());
    }
}
