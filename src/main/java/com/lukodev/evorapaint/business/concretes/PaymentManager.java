package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.PaymentService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.PaymentDao;
import com.lukodev.evorapaint.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;

    @Autowired
    public PaymentManager(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    @CacheEvict(value = "payment.getAll", allEntries = true)
    public Result add(Payment payment) {
        this.paymentDao.save(payment);
        return new SuccessResult(Messages.PAYMENT_ADDED);
    }

    @Override
    @CacheEvict(value = "payment.getAll", allEntries = true)
    public Result update(Payment payment) {
        this.paymentDao.save(payment);
        return new SuccessResult(Messages.PAYMENT_UPDATED);
    }

    @Override
    @CacheEvict(value = "payment.getAll", allEntries = true)
    public Result delete(Payment payment) {
        this.paymentDao.delete(payment);
        return new SuccessResult(Messages.PAYMENT_DELETED);
    }

    @Override
    @Cacheable(value = "payment.getAll")
    public DataResult<List<Payment>> getAll() {
        return new SuccessDataResult<>(this.paymentDao.findAll());
    }

    @Override
    public DataResult<List<Payment>> getAllByOrderId(int orderId) {
        return new SuccessDataResult<>(this.paymentDao.getAllByOrderId(orderId));
    }

    @Override
    public DataResult<Payment> getById(int id) {
        return new SuccessDataResult<>(this.paymentDao.findById(id).get());
    }
}
