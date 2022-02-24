package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Payment;

import java.util.List;

public interface PaymentService {

    Result add(Payment payment);
    Result update(Payment payment);
    Result delete(Payment payment);

    DataResult<List<Payment>> getAll();
    DataResult<List<Payment>> getAllByOrderId(int orderId);

    DataResult<Payment> getById(int id);

}
