package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Order;

import java.util.List;

public interface OrderService {

    Result add(Order order);
    Result update(Order order);
    Result delete(Order order);

    DataResult<List<Order>> getAll();
    DataResult<List<Order>> getAllByCustomerId(int customerId);
    DataResult<List<Order>> getAllByOrderStatusId(int orderStatusId);

    DataResult<Order> getById(int id);

}
