package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.OrderProduct;
import com.lukodev.evorapaint.entities.dtos.ProductWithImageDto;

import java.util.List;

public interface OrderProductService {

    Result add(OrderProduct orderProduct);
    Result addAll(List<OrderProduct> orderProducts);
    Result update(OrderProduct orderProduct);
    Result delete(OrderProduct orderProduct);

    DataResult<List<OrderProduct>> getAll();
    DataResult<List<OrderProduct>> getAllByOrderId(int orderId);


    DataResult<OrderProduct> getById(int id);

}
