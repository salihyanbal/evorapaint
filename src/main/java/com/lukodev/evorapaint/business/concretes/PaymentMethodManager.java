package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.PaymentMethodService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.PaymentMethodDao;
import com.lukodev.evorapaint.entities.concretes.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodManager implements PaymentMethodService {

    private PaymentMethodDao paymentMethodDao;

    @Autowired
    public PaymentMethodManager(PaymentMethodDao paymentMethodDao) {
        this.paymentMethodDao = paymentMethodDao;
    }

    @Override
    @CacheEvict(value = "paymentMethod.getAll",allEntries = true)
    public Result add(PaymentMethod paymentMethod) {
        this.paymentMethodDao.save(paymentMethod);
        return new SuccessResult(Messages.PAYMENT_METHOD_ADDED);
    }

    @Override
    @CacheEvict(value = "paymentMethod.getAll",allEntries = true)
    public Result update(PaymentMethod paymentMethod) {
        this.paymentMethodDao.save(paymentMethod);
        return new SuccessResult(Messages.PAYMENT_METHOD_UPDATED);
    }

    @Override
    @CacheEvict(value = "paymentMethod.getAll",allEntries = true)
    public Result delete(PaymentMethod paymentMethod) {
        this.paymentMethodDao.delete(paymentMethod);
        return new SuccessResult(Messages.PAYMENT_METHOD_DELETED);
    }

    @Override
    @Cacheable(value = "paymentMethod.getAll")
    public DataResult<List<PaymentMethod>> getAll() {
        return new SuccessDataResult<>(this.paymentMethodDao.findAll());
    }

    @Override
    public DataResult<PaymentMethod> getById(int id) {
        return new SuccessDataResult<>(this.paymentMethodDao.findById(id).get());
    }
}
