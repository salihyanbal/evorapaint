package com.lukodev.evorapaint.business.abstracts;

import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.entities.concretes.Company;

import java.util.List;

public interface CompanyService {

    Result add(Company company);
    Result update(Company company);
    Result delete(Company company);

    DataResult<List<Company>> getAll();
    DataResult<Company> getById(int id);
    DataResult<Company> getByCustomerId(int customerId);

    DataResult<Boolean> existByCustomerId(int customerId);

}
