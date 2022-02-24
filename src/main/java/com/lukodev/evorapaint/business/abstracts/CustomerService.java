package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Customer;

import java.util.List;

public interface CustomerService {

    Result add(Customer customer);
    Result update(Customer customer);
    Result delete(Customer customer);

    DataResult<List<Customer>> getAll();
    DataResult<Customer> getById(int id);

}
