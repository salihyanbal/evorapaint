package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    Result add(OrderStatus orderStatus);
    Result update(OrderStatus orderStatus);
    Result delete(OrderStatus orderStatus);

    DataResult<List<OrderStatus>> getAll();
    DataResult<OrderStatus> getById(int id);

}
