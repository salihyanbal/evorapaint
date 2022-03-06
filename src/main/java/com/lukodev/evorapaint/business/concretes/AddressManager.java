package com.lukodev.evorapaint.business.concretes;

import com.lukodev.evorapaint.business.abstracts.AddressService;
import com.lukodev.evorapaint.business.constants.Messages;
import com.lukodev.evorapaint.core.utilities.results.DataResult;
import com.lukodev.evorapaint.core.utilities.results.Result;
import com.lukodev.evorapaint.core.utilities.results.SuccessDataResult;
import com.lukodev.evorapaint.core.utilities.results.SuccessResult;
import com.lukodev.evorapaint.dataAccess.abstracts.AddressDao;
import com.lukodev.evorapaint.entities.concretes.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressManager implements AddressService {

    private AddressDao addressDao;

    @Autowired
    public AddressManager(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    @CacheEvict(value = "address.getAll",allEntries = true)
    public Result add(Address address) {
        this.addressDao.save(address);
        return new SuccessResult(Messages.ADDRESS_ADDED);
    }

    @Override
    @CacheEvict(value = "address.getAll",allEntries = true)
    public Result update(Address address) {
        this.addressDao.save(address);
        return new SuccessResult(Messages.ADDRESS_UPDATED);
    }

    @Override
    @CacheEvict(value = "address.getAll",allEntries = true)
    public Result delete(Address address) {
        this.addressDao.delete(address);
        return new SuccessResult(Messages.ADDRESS_DELETED);
    }

    @Override
    @Cacheable(value = "address.getAll")
    public DataResult<List<Address>> getAll() {
        return new SuccessDataResult<>(this.addressDao.findAll());
    }

    @Override
    public DataResult<List<Address>> getAllByCustomerId(int customerId) {
        return new SuccessDataResult<>(this.addressDao.getAllByCustomerId(customerId));
    }

    @Override
    public DataResult<List<Address>> getAllByCustomerIdAndDeletedFalse(int customerId) {
        return new SuccessDataResult<>(this.addressDao.getAllByCustomerIdAndDeletedFalse(customerId));
    }

    @Override
    public DataResult<List<Address>> getAllByPageNumber(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber-1,10);
        return new SuccessDataResult<>(this.addressDao.findAll(page).toList());
    }

    @Override
    public DataResult<Address> getById(int id) {
        return new SuccessDataResult<>(this.addressDao.findById(id).get());
    }
}
