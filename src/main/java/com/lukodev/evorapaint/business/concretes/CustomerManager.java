package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.CustomerService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.CustomerDao;
import com.lukodev.evorapaint.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerManager(CustomerDao customerDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result add(Customer customer) {
        this.customerDao.save(customer);
        return new SuccessResult(Messages.CUSTOMER_ADDED);
    }

    @Override
    public Result update(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        this.customerDao.save(customer);
        return new SuccessResult(Messages.CUSTOMER_UPDATED);
    }

    @Override
    public Result delete(Customer customer) {
        this.customerDao.delete(customer);
        return new SuccessResult(Messages.CUSTOMER_DELETED);
    }

    @Override
    public DataResult<List<Customer>> getAll() {
        return new SuccessDataResult<>(this.customerDao.findAll());
    }

    @Override
    public DataResult<Customer> getById(int id) {
        return new SuccessDataResult<>(this.customerDao.findById(id).get());
    }
}
