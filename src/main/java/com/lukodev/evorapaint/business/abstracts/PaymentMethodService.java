package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {

    Result add(PaymentMethod paymentMethod);
    Result update(PaymentMethod paymentMethod);
    Result delete(PaymentMethod paymentMethod);

    DataResult<List<PaymentMethod>> getAll();
    DataResult<PaymentMethod> getById(int id);

}
