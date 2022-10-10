package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.CompanyService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.CompanyDao;
import com.lukodev.evorapaint.entities.concretes.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyManager implements CompanyService {

    private CompanyDao companyDao;

    @Autowired
    public CompanyManager(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public Result add(Company company) {
        this.companyDao.save(company);
        return new SuccessResult(Messages.COMPANY_ADDED);
    }

    @Override
    public Result update(Company company) {
        this.companyDao.save(company);
        return new SuccessResult(Messages.COMPANY_UPDATED);
    }

    @Override
    public Result delete(Company company) {
        this.companyDao.delete(company);
        return new SuccessResult(Messages.COMPANY_DELETED);
    }

    @Override
    public DataResult<List<Company>> getAll() {
        return new SuccessDataResult<>(this.companyDao.findAll());
    }

    @Override
    public DataResult<Company> getById(int id) {
        return new SuccessDataResult<>(this.companyDao.findById(id).get());
    }

    @Override
    public DataResult<Company> getByCustomerId(int customerId) {
        return new SuccessDataResult<>(this.companyDao.getByCustomerId(customerId));
    }

    @Override
    public DataResult<Boolean> existByCustomerId(int customerId) {
        return new SuccessDataResult<>(this.companyDao.existsByCustomerId(customerId));
    }
}
