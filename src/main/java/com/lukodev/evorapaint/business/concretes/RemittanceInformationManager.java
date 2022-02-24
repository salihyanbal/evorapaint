package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.RemittanceInformationService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.RemittanceInformationDao;
import com.lukodev.evorapaint.entities.concretes.RemittanceInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemittanceInformationManager implements RemittanceInformationService {

    private RemittanceInformationDao remittanceInformationDao;

    @Autowired
    public RemittanceInformationManager(RemittanceInformationDao remittanceInformationDao) {
        this.remittanceInformationDao = remittanceInformationDao;
    }

    @Override
    @CacheEvict(value = "remittanceInformation.getAll",allEntries = true)
    public Result add(RemittanceInformation remittanceInformation) {
        this.remittanceInformationDao.save(remittanceInformation);
        return new SuccessResult(Messages.REMITTANCE_INFORMATION_ADDED);
    }

    @Override
    @CacheEvict(value = "remittanceInformation.getAll",allEntries = true)
    public Result update(RemittanceInformation remittanceInformation) {
        this.remittanceInformationDao.save(remittanceInformation);
        return new SuccessResult(Messages.REMITTANCE_INFORMATION_UPDATED);
    }

    @Override
    @CacheEvict(value = "remittanceInformation.getAll",allEntries = true)
    public Result delete(RemittanceInformation remittanceInformation) {
        this.remittanceInformationDao.delete(remittanceInformation);
        return new SuccessResult(Messages.REMITTANCE_INFORMATION_DELETED);
    }

    @Override
    @CacheEvict(value = "remittanceInformation.getAll")
    public DataResult<List<RemittanceInformation>> getAll() {
        return new SuccessDataResult<>(this.remittanceInformationDao.findAll());
    }

    @Override
    public DataResult<List<RemittanceInformation>> getAllByPaymentMethodId(int paymentMethodId) {
        return new SuccessDataResult<>(this.remittanceInformationDao.getAllByPaymentMethodId(paymentMethodId));
    }

    @Override
    public DataResult<RemittanceInformation> getById(int id) {
        return new SuccessDataResult<>(this.remittanceInformationDao.findById(id).get());
    }
}
